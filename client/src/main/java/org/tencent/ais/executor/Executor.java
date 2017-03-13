package org.tencent.ais.executor;

import org.tencent.ais.communication.client.MasterExecutorServiceProtocolClient;
import org.tencent.ais.execute.TaskRunner;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.task.TaskInfo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by iwardzhong on 2017/2/28.
 */
public abstract class Executor {
  protected MasterExecutorServiceProtocolClient mespc = new MasterExecutorServiceProtocolClient();
  protected String executorId;
  protected String executorHostname;
  protected TaskInfo taskInfo;
  protected boolean isRun = false;
  protected TaskRunner taskRunner;
  protected ExecutorInfo executorInfo = new ExecutorInfo();
  protected ResourceInfo resourceInfo = new ResourceInfo();

  public Executor(String executorId, String hostname) {
    this.executorId = executorId;
    this.executorHostname = hostname;
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

  protected abstract void run(ExecutorInfo executorInfo);
  public abstract void launchTask();
  public abstract void reportHeartbeat(ExecutorInfo executorInfo);
  public abstract void killTask();
  public abstract void stop();

  public String getCurrentProcessID() {
    RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    String pid = runtimeMXBean.getName().split("@")[0];
    return pid;
  }

}
