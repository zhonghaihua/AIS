package org.tencent.ais.task;

import org.tencent.ais.data.manager.TaskData;

import java.util.List;

/**
 * Created by iwardzhong on 2017/2/21.
 */
public class TaskInfo {
  public TaskData taskData;
  public String TaskClientIp;
  public int platform;
  public List<String> machineList; // for mpi

  public TaskData getTaskData() {
    return taskData;
  }

  public void setTaskData(TaskData taskData) {
    this.taskData = taskData;
  }

  public String getTaskClientIp() {
    return TaskClientIp;
  }

  public void setTaskClientIp(String taskClientIp) {
    TaskClientIp = taskClientIp;
  }

  public int getPlatform() {
    return platform;
  }

  public void setPlatform(int platform) {
    this.platform = platform;
  }

  public List<String> getMachineList() {
    return machineList;
  }

  public void setMachineList(List<String> machineList) {
    this.machineList = machineList;
  }
}
