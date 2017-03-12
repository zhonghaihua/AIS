package org.tencent.ais.executor;

import org.tencent.ais.task.TaskInfo;

/**
 * Created by iwardzhong on 2017/2/28.
 */
public abstract class Executor {
  protected String executorId;
  protected String executorHostname;
  protected TaskInfo taskInfo;

  public Executor(String executorId, String hostname, TaskInfo taskInfo) {
    this.executorId = executorId;
    this.executorHostname = hostname;
    this.taskInfo = taskInfo;
  }

  public String getExecutorId() {
    return executorId;
  }

  public void setExecutorId(String executorId) {
    this.executorId = executorId;
  }

  public String getExecutorHostname() {
    return executorHostname;
  }

  public void setExecutorHostname(String executorHostname) {
    this.executorHostname = executorHostname;
  }

  public TaskInfo getTaskInfo() {
    return taskInfo;
  }

  public void setTaskInfo(TaskInfo taskInfo) {
    this.taskInfo = taskInfo;
  }

  public abstract void runTask(TaskInfo taskInfo);
  public abstract void reportHeartbeat();
  public abstract void killTask();
  public abstract void stop();

}
