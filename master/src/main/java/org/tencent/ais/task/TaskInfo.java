package org.tencent.ais.task;

import org.tencent.ais.data.manager.TaskData;

/**
 * Created by iwardzhong on 2017/2/21.
 */
public class TaskInfo {
  public TaskData taskData;
  public String TaskClientIp;
  public int platform;

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
}
