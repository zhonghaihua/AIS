package org.tencent.ais.executor;

import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.execute.TaskRunner;
import org.tencent.ais.monitor.TaskMonitorManager;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.util.SystemInfoUtils;

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
    launchTask();
    executorInfo.setExecutorHostname(executorHostname);
    executorInfo.setExecutorId(executorId);
    String taskId;
    if (taskInfo.getTaskData().getDataType() == 0) {
      taskId = String.valueOf(taskInfo.taskData.getAccessId());
    } else {
      taskId = String.valueOf(taskInfo.taskData.getTaskId());
    }
    executorInfo.setTaskId(taskId);
    executorInfo.setLastUpdate(System.currentTimeMillis());
    run(executorInfo);
  }

  @Override
  protected void run(ExecutorInfo executorInfo) {
    this.taskRunner.start();
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
  }

  private void handleHeartbeatResponse(Map<String, String> res) {
    String cmd = res.get("cmd");
    if (cmd.equals("kill")) {
      stop();
    } else if (cmd.equals("run")) {
      executorInfo.setLastUpdate(Long.parseLong(res.get(executorId)));
    }
  }

  @Override
  public void launchTask() {
    ExecutorInfo executorInfo = new ExecutorInfo();
    executorInfo.setExecutorId(this.executorId);
    executorInfo.setExecutorHostname(this.executorHostname);
    this.taskInfo = this.mespc.launchTaskToRun(executorInfo);
    this.taskRunner = new TaskRunner(taskInfo, executorId);
  }

  @Override
  public void reportHeartbeat(ExecutorInfo executorInfo) {
    Map<String, String> res = this.mespc.reportHeartbeat(executorInfo);
    handleHeartbeatResponse(res);
  }

  @Override
  public void killTask() {
    TaskMonitorManager.getInstance().killTask();
    this.taskRunner.destroy();
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
  }
}
