package org.tencent.ais.executor;

import org.tencent.ais.resource.ResourceInfo;

/**
 * Created by iwardzhong on 2017/2/28.
 */
public class ExecutorInfo {
  public String executorId;
  public String executorHostname;
  public String taskId;
  public long lastUpdate;
  public ResourceInfo executorResourceInfo;

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

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public ResourceInfo getExecutorResourceInfo() {
    return executorResourceInfo;
  }

  public long getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(long lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public void setExecutorResourceInfo(ResourceInfo executorResourceInfo) {
    this.executorResourceInfo = executorResourceInfo;
  }
}
