package org.tencent.ais.task.event;

import org.tencent.ais.task.TaskInfo;

/**
 * Created by iwardzhong on 2017/2/22.
 */
public class UpdateTaskEvent implements Event {

  private TaskInfo taskInfo;

  public UpdateTaskEvent(TaskInfo info) {
    this.taskInfo = info;
  }

  @Override
  public TaskInfo getTaskInfo() {
    return taskInfo;
  }
}
