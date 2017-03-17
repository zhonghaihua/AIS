package org.tencent.ais.executor;

import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.execute.TaskRunner;
import org.tencent.ais.monitor.TaskMonitorManager;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.util.SystemInfoUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by iwardzhong on 2017/3/3.
 */
public class MPIExecutor extends Executor {

  private Logger log = LoggerFactory.getLogger("MPIExecutor");

  public MPIExecutor(String executorId, String executorHostname) {
    super(executorId, executorHostname);
    init();
  }

  private void init() {
    TaskMonitorManager.getInstance().setRun(true);
    executorInfo.setExecutorHostname(executorHostname);
    executorInfo.setExecutorId(executorId);
    executorInfo.setLastUpdate(System.currentTimeMillis());
    launchTask();
    run(executorInfo);
  }

  @Override
  protected void run(ExecutorInfo executorInfo) {
    startTaskQueue();
    while (TaskMonitorManager.getInstance().isRun()) {
      resourceInfo.setFreecpu(10);
      resourceInfo.setPlatformFreememory(0);
      resourceInfo.setTotalcpu(10);
      resourceInfo.setBusycpu(10);
      try {
        resourceInfo.setTotalmemory(SystemInfoUtils.getTotalMemory());
        resourceInfo.setFreememory(SystemInfoUtils.getFreeMemory());
      } catch (SigarException e) {
        log.error("Get system info failed " + e.getMessage());
      }
      executorInfo.setExecutorResourceInfo(resourceInfo);
      reportHeartbeat(executorInfo);
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    stopTaskQueue();
  }

  private void handleHeartbeatResponse(Map<String, String> res) {
    String cmd = res.get("cmd");
    System.out.println("heartbeat response: " + cmd);
    if (cmd.equals("kill")) {
      stop();
    } else if (cmd.equals("run")) {
      executorInfo.setLastUpdate(Long.parseLong(res.get(executorId)));
    } else if (cmd.equals("launch")) {
      System.out.println("other task run in same executor");
      executorInfo.setLastUpdate(Long.parseLong(res.get(executorId)));
      launchTask();
    }
  }

  @Override
  public void launchTask() {
    TaskInfo task = this.mespc.launchTaskToRun(executorInfo);
    int taskId;
    if (task.getTaskData().getDataType() == 0) {
      taskId = task.getTaskData().getAccessId();
    } else {
      taskId = task.getTaskData().getTaskId();
    }
    this.taskInfo.put(taskId, task);
    try {
      this.taskRunnerQueue.put(new TaskRunner(task, executorId));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void reportHeartbeat(ExecutorInfo executorInfo) {
    Map<String, String> res = this.mespc.reportHeartbeat(executorInfo);
    handleHeartbeatResponse(res);
  }

  @Override
  public void killTask() {
    TaskMonitorManager.getInstance().killTask();
    Iterator<TaskRunner> it = this.taskRunnerQueue.iterator();
    while (it.hasNext()) {
      TaskRunner tr = it.next();
      tr.destroy();
    }
  }

  @Override
  public void stop() {
    killTask();
    TaskMonitorManager.getInstance().setRun(false);
  }


  public static void main(String argv[]) throws Exception {
    String executorId = argv[0];
    String path = System.getProperty("java.library.path");
    System.out.println(path);
//    String executorId = "1";
    System.out.println("start run mpi");
    new MPIExecutor(executorId, SystemInfoUtils.getLocalHostIp());
    stop = true;
    System.out.println("end");
  }
}
