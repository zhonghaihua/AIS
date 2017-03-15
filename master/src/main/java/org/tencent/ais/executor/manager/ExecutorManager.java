package org.tencent.ais.executor.manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.data.util.CommonConf;
import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.event.FailedTaskEvent;
import org.tencent.ais.task.scheduler.TaskScheduler;

import java.util.HashMap;
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
  private Map<String, TaskInfo> executorIdToTaskInfo = new HashMap<>();
  private Map<String, Process> executorIdToProcess = new HashMap<>();
  private Map<String, String> executorIdToTaskPid = new HashMap<>();
  private Map<String, String> executorIdToPid = new HashMap<>();

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
      res.put("cmd", "run");
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


  private void killExecutor(String executorId, String clientIp) {
    // kill the executor
    Process process = executorIdToProcess.get(executorId);
    process.destroy();
    // 将这个executor的任务的状态全部修改为失败状态
    Event event = new FailedTaskEvent(executorIdToTaskInfo.get(executorId));
    try {
      TaskScheduler.getTaskSchedulerInstance().getEventProcessLoop().post(event);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void addExecutorIdToIp(String executorId, String ip) {
    executorIdToIp.put(executorId, ip);
  }

  public void setExecutorIdToTaskInfo(String executorId, TaskInfo taskInfo) {
    executorIdToTaskInfo.put(executorId, taskInfo);
  }

  public void setExecutorLastseen(String executorId, Long firstTime) {
    executorLastseen.put(executorId, firstTime);
  }

  public void setExecutorIdToProcess(String executorId, Process process) {
    executorIdToProcess.put(executorId, process);
  }

  public void setExecutorIdToTaskPidAndExecutorPid(String executorId, String taskPid, String executorPid) {
    executorIdToTaskPid.put(executorId, taskPid);
    executorIdToPid.put(executorId, executorPid);
  }

}
