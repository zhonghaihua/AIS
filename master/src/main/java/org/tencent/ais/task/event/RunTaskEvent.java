package org.tencent.ais.task.event;

import org.tencent.ais.task.TaskInfo;

/**
 * Created by iwardzhong on 2017/2/22.
 */
public class RunTaskEvent implements Event {

  private TaskInfo taskInfo;

  public RunTaskEvent(TaskInfo info) {
    this.taskInfo = info;
  }

  @Override
  public TaskInfo getTaskInfo() {
    return taskInfo;
  }

}
