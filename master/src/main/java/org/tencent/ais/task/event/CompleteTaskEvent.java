package org.tencent.ais.task.event;

import org.tencent.ais.task.TaskInfo;

/**
 * Created by iwardzhong on 2017/2/22.
 */
public class CompleteTaskEvent implements Event {
  private  TaskInfo taskInfo;

  public CompleteTaskEvent(TaskInfo info) {
    this.taskInfo = info;
  }

  @Override
  public TaskInfo getTaskInfo() {
    return taskInfo;
  }
}
