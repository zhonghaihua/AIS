package org.tencent.ais.execute;


import org.tencent.ais.data.manager.TaskData;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.util.AISUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iwardzhong on 2017/3/1.
 */
public class TaskRunner extends Thread {
  private TaskInfo taskInfo;
  private TaskData taskData;

  public TaskRunner(TaskInfo taskInfo) {
    this.taskInfo = taskInfo;
    this.taskData = taskInfo.getTaskData();
  }

  private void runTask() {
    int taskType = taskData.getDataType();
    Map<String, String> cmdres = prepareCommand(taskType);
    String precmd = cmdres.get("precmd");
    String runcmd = cmdres.get("runcmd");
    try {
      AISUtils.execuShellCmd(precmd);
      AISUtils.execuShellCmd(runcmd);
    } catch (IOException e) {
      e.printStackTrace();
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
    String package_name = package_path.substring(package_path.lastIndexOf("/"), package_path.length());
    String input_path = taskData.getInputPath();
    String output_path = taskData.getOutputPath();
    String parameter_path = taskData.getParameterPath();
    int task_type = taskData.getTaskType();
    String workdir = "/data/workdir/data_tmp/" + taskId;
    String precmd = "mkdir " + workdir + " && cp " + package_path + " " + workdir + " && cd " + workdir + " && tar -xf " + package_name;
    String runcmd = "cd " + workdir + " && nohup ./submit_run.sh " + package_path + " " +
            input_path + " " + output_path + " " + parameter_path + " " + taskId + " " +
            task_type + " > ../%s.log 2>&1 &";

    cmdMap.put("precmd", precmd);
    cmdMap.put("runcmd", runcmd);

    return cmdMap;
  }


  @Override
  public void run() {

  }
}
