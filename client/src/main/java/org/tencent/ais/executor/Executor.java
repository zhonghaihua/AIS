package org.tencent.ais.executor;

import org.tencent.ais.communication.client.MasterExecutorServiceProtocolClient;
import org.tencent.ais.execute.TaskRunner;
import org.tencent.ais.monitor.TaskMonitorManager;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.task.TaskInfo;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by iwardzhong on 2017/2/28.
 */
public abstract class Executor {
  protected MasterExecutorServiceProtocolClient mespc = new MasterExecutorServiceProtocolClient();
  protected String executorId;
  protected String executorHostname;
  protected Map<Integer, TaskInfo> taskInfo = new HashMap<>();
  protected LinkedBlockingQueue<TaskRunner> taskRunnerQueue = new LinkedBlockingQueue<>();
  protected ResourceInfo resourceInfo = new ResourceInfo();
  protected ExecutorInfo executorInfo = new ExecutorInfo();
  public static volatile boolean stop = false;
  private Thread taskQueue = new Thread(new Runnable() {
    @Override
    public void run() {
      System.out.println("strat handle task queue");
      while (!stop) {
        try {
          TaskRunner taskRunner = taskRunnerQueue.take();
          taskRunner.start();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("task queue handle finished");
    }
  });

  public Executor(String executorId, String hostname) {
    this.executorId = executorId;
    this.executorHostname = hostname;
    TaskMonitorManager.getInstance().setExecutorId(executorId);
    TaskMonitorManager.getInstance().setCurrenProcessPid(getCurrentProcessID());
    TaskMonitorManager.getInstance().setMespc(mespc);
    TaskMonitorManager.getInstance().setTaskRunnerQueue(taskRunnerQueue);
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

  public TaskInfo getTaskInfo(int taskId) {
    return taskInfo.get(taskId);
  }

  public void startTaskQueue() {
    taskQueue.start();
  }

  public void stopTaskQueue() {
    mespc.executorRelease(executorId, executorHostname);
    stop = true;
    taskQueue.stop();
  }

  public void setTaskInfo(int taskId, TaskInfo taskInfo) {
    this.taskInfo.put(taskId, taskInfo);
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
