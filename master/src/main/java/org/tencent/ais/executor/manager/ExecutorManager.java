package org.tencent.ais.executor.manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.data.manager.TaskData;
import org.tencent.ais.data.util.CommonConf;
import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.event.FailedTaskEvent;
import org.tencent.ais.task.scheduler.TaskScheduler;
import org.tencent.ais.util.AISUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by iwardzhong on 2017/2/28.
 */
public class ExecutorManager {
  private Logger log = LoggerFactory.getLogger("ExecutorManager");
  private long executorTimeout = CommonConf.EXECUTOR_TIMEOUT;
  private static ExecutorManager executorManagerInstance = null;
  private Map<String, Long> executorLastseen = new HashMap<>();
  private Map<String, String> executorIdToIp = new HashMap<>();
  private Map<String, ResourceInfo> clientResource = new ConcurrentHashMap<>();
  private Map<String, List<TaskInfo>> executorIdToTaskInfo = new HashMap<>();
  private Map<String, Process> executorIdToProcess = new HashMap<>();
  private Map<String, String> executorIdToTaskPid = new HashMap<>();
  private Map<String, String> executorIdToPid = new HashMap<>();
  private Map<String, Integer> executorLaunchTaskInHeartbeat = new ConcurrentHashMap<>();

  // 主要针对客户端一直Executor卡住的情况，其余心跳超时都是发送kill命令去kill
  private Thread checkExecutorHeartbeat = new Thread(new Runnable() {
    @Override
    public void run() {
      for (String id : executorLastseen.keySet()) {
        Long now = System.currentTimeMillis();
        Long lastseen = executorLastseen.get(id);
        if (now - lastseen > executorTimeout) {
          log.error("Executor：" + id + "is heartbeat timeout, killed by master");
          killExecutor(id, executorIdToIp.get(id));
          executorLastseen.remove(id);
        }
      }
      try {
        Thread.sleep(300000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  });

  private ExecutorManager() {
    init();
  }

  private void init() {
    checkExecutorHeartbeat.start();
  }

  public static ExecutorManager getInstance() {
    if (executorManagerInstance == null) {
      synchronized (ExecutorManager.class) {
        executorManagerInstance = new ExecutorManager();
      }
    }
    return executorManagerInstance;
  }

  public Map<String, String> handleExecutorHeartbeat(ExecutorInfo executorInfo) {
    boolean isDeadExecutor = expireDeadExecutor(executorInfo);
    Map<String, String> res = new HashMap<>();
    if (isDeadExecutor) {
      log.warn("Executor: " + executorInfo.getExecutorId() + "is heartbeat timeout, send kill command to executor.");
      res.put("cmd", "kill");
    } else {
      if (executorLaunchTaskInHeartbeat.containsKey(executorInfo.getExecutorId()) &&
              executorLaunchTaskInHeartbeat.get(executorInfo.getExecutorId()) > 0) {
        decreaseExecutorLaunchTaskInHeartbeat(executorInfo.getExecutorId());
        res.put("cmd", "launch");
        System.out.println("Executor: " + executorInfo.getExecutorId() + " launch another task");
      } else {
        res.put("cmd", "run");
      }
    }
    String updateTime = String.valueOf(executorLastseen.get(executorInfo.getExecutorId()));
    res.put(executorInfo.getExecutorId(), updateTime);
    clientResource.put(executorInfo.getExecutorHostname(), executorInfo.executorResourceInfo);

    return res;
  }

  private boolean expireDeadExecutor(ExecutorInfo executorInfo) {
    long lastSeen = executorInfo.getLastUpdate();
    long now = System.currentTimeMillis();
    if (now - lastSeen > executorTimeout) {
      executorLastseen.remove(executorInfo.getExecutorId());
      return true;
    } else {
      executorLastseen.put(executorInfo.getExecutorId(), now);
      return false;
    }
  }


  public void killExecutor(String executorId, String clientIp) {
    // 将这个executor的任务的状态全部修改为失败状态并且发送kill命令
    for (TaskInfo taskInfo : executorIdToTaskInfo.get(executorId)) {
      killTask(taskInfo);
      Event event = new FailedTaskEvent(taskInfo);
      try {
        TaskScheduler.getTaskSchedulerInstance().getEventProcessLoop().post(event);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // kill the executor
    Process process = executorIdToProcess.get(executorId);
    process.destroy();
    String executorPid = executorIdToPid.get(executorId);
    String killcmd = "ssh root@" + clientIp + " 'kill -9 " + executorPid + "'";
    try {
      AISUtils.asynExecuShellCmd(killcmd);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 从clientToexecutor中移除
    TaskScheduler.getTaskSchedulerInstance().killExecutor(clientIp, executorId);
  }

  public void killTask(TaskInfo taskInfo) {
    TaskData taskData = taskInfo.getTaskData();
    int taskId;
    if (taskData.getDataType() == 0) {
      taskId = taskData.getAccessId();
    } else {
      taskId = taskData.getTaskId();
    }
    String platformId = String.valueOf(taskData.getPlatformId());
    String clientIp = taskInfo.getTaskClientIp();
    String killcmd = "cd /data/iward/ais/bin/ && ./killTask -platformId " +
            platformId + " -client " + clientIp + " -taskId " + taskId;
    try {
      AISUtils.execuShellCmd(killcmd);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void killAllTask() {
    for (String executorId : executorIdToTaskInfo.keySet()) {
      List<TaskInfo> list = executorIdToTaskInfo.get(executorId);
      for (TaskInfo taskInfo : list) {
        killTask(taskInfo);
      }
    }
  }

  public void addExecutorIdToIp(String executorId, String ip) {
    executorIdToIp.put(executorId, ip);
  }

  public void setExecutorIdToTaskInfo(String executorId, TaskInfo taskInfo) {
    if (executorIdToTaskInfo.keySet().contains(executorId)) {
      List<TaskInfo> list = executorIdToTaskInfo.get(executorId);
      list.add(taskInfo);
      executorIdToTaskInfo.put(executorId, list);
    } else {
      List<TaskInfo> list = new ArrayList<>();
      list.add(taskInfo);
      executorIdToTaskInfo.put(executorId, list);
    }
  }

  public void removeExecutorIdTaskInfo(String executorId, TaskInfo taskInfo) {
    if (executorIdToTaskInfo.containsKey(executorId)) {
      List<TaskInfo> list = executorIdToTaskInfo.get(executorId);
      if (list.contains(taskInfo) && list.size() == 1) {
        executorIdToTaskInfo.remove(executorId);
      } else if (list.contains(taskInfo) && list.size() > 1) {
        list.remove(taskInfo);
        executorIdToTaskInfo.put(executorId, list);
      }
    }
  }

  public void setExecutorLastseen(String executorId, Long firstTime) {
    executorLastseen.put(executorId, firstTime);
  }

  public void setExecutorIdToProcess(String executorId, Process process) {
    executorIdToProcess.put(executorId, process);
  }

  public synchronized void setExecutorLaunchTaskInHeartbeat(String executorId) {
    int count = 0;
    if (executorLaunchTaskInHeartbeat.containsKey(executorId)) {
      count = executorLaunchTaskInHeartbeat.get(executorId) + 1;
    } else {
      count += 1;
    }
    executorLaunchTaskInHeartbeat.put(executorId, count);
  }

  public synchronized void decreaseExecutorLaunchTaskInHeartbeat(String executorId) {
    int count = executorLaunchTaskInHeartbeat.get(executorId) - 1;
    executorLaunchTaskInHeartbeat.put(executorId, count);
  }

  public void setExecutorIdToTaskPidAndExecutorPid(String executorId, String taskPid, String executorPid) {
    executorIdToTaskPid.put(executorId, taskPid);
    if (!executorIdToPid.containsKey(executorId)) {
      executorIdToPid.put(executorId, executorPid);
    }
  }

}
