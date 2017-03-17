package org.tencent.ais.execute;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.data.manager.TaskData;
import org.tencent.ais.monitor.TaskMonitorManager;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.util.AISUtils;
import org.tencent.ais.util.SystemInfoUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iwardzhong on 2017/3/1.
 */
public class TaskRunner extends Thread {
  private Logger log = LoggerFactory.getLogger("TaskRunner");
  private TaskInfo taskInfo;
  private TaskData taskData;
  private String executorId;

  public TaskRunner(TaskInfo taskInfo, String executorId) {
    this.taskInfo = taskInfo;
    this.executorId = executorId;
    this.taskData = taskInfo.getTaskData();
  }

  private void runTask() {
    int taskType = taskData.getDataType();
    Map<String, String> cmdres = prepareCommand(taskType);
    String precmd = cmdres.get("precmd");
    String runcmd = cmdres.get("runcmd");
    System.out.println("cmd1: " + precmd);
    System.out.println("cmd2: " + runcmd);
    log.info("cmd1: " + precmd);
    log.info("cmd2: " + runcmd);
    String pid = null;
    try {
      AISUtils.execuShellCmd(precmd);
      System.out.println("executor runtask");
      pid = AISUtils.asynExecuShellCmd(runcmd);
    } catch (IOException e) {
      e.printStackTrace();
    }
    TaskMonitorManager.getInstance().setTaskPid(pid);
    TaskMonitorManager.getInstance().sendTaskPidAndExecutorPid();
    TaskMonitorManager.getInstance().setTaskInfo(taskInfo);
    if (!TaskMonitorManager.getInstance().threadIsAlive()) {
      TaskMonitorManager.getInstance().startTaskMoniterThread();
    }
  }

  private Map<String, String> prepareCommand(int taskType) {
    Map<String, String> cmdMap= new HashMap<>();
    int taskId;
    if (taskType == 0) {
      taskId = taskData.getAccessId();
    } else {
      taskId = taskData.getTaskId();
    }
    String package_path = taskData.getPackagePath();
    String package_name = package_path.substring(package_path.lastIndexOf("/") + 1, package_path.length());
    String input_path = taskData.getInputPath();
    String output_path = taskData.getOutputPath();
    String parameter_path = taskData.getParameterPath();
    int task_type = taskData.getTaskType();
    String workdir = "/data/workdir/data_tmp/" + executorId + "/" + taskId;
    String precmd = "mkdir -p " + workdir + "; cp " + package_path + " " + workdir + " && cd " + workdir + " && tar -xf " + package_name;
    String runcmd = "cd " + workdir + " && nohup ./submit_run.sh " + package_path + " " +
            input_path + " " + output_path + " " + parameter_path + " " + taskId + " " +
            task_type + " > " + taskId + ".log 2>&1 &";

    cmdMap.put("precmd", precmd);
    cmdMap.put("runcmd", runcmd);
    cmdMap.put("workdir", workdir);

    return cmdMap;
  }

  @Override
  public void run() {
    runTask();
  }
}
