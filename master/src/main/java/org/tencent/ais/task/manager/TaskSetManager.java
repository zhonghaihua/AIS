package org.tencent.ais.task.manager;

import org.tencent.ais.task.TaskInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by iwardzhong on 2017/2/21.
 */
public class TaskSetManager {
  public LinkedList<TaskInfo> taskSetInitial; //可能会被删掉
  private LinkedBlockingQueue<TaskInfo> taskSetToRunn = new LinkedBlockingQueue<>();
  private LinkedBlockingQueue<TaskInfo> taskSetSuccess = new LinkedBlockingQueue<>();
  private LinkedBlockingQueue<TaskInfo> taskSetFailed = new LinkedBlockingQueue<>();
  private Map<String, TaskInfo> prepareToRun = new HashMap<>();
  private static TaskSetManager taskSetManagerInstance = null;

  private TaskSetManager() {}

  public static TaskSetManager getTaskSetManagerInstance() {
    if (taskSetManagerInstance == null) {
      synchronized (TaskSetManager.class) {
        taskSetManagerInstance = new TaskSetManager();
      }
    }
    return taskSetManagerInstance;
  }


  public TaskInfo getTaskInfoFromTaskSetToRun() {
    TaskInfo taskInfo = null;
    try {
       taskInfo = taskSetToRunn.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return taskInfo;
  }

  public void setTaskInfoToTaskSetToRun(TaskInfo taskInfo) throws InterruptedException {
    taskSetToRunn.put(taskInfo);
  }

  public void setTaskInfoToTaskSetSuccess(TaskInfo taskInfo) throws InterruptedException {
    taskSetSuccess.put(taskInfo);
  }

  public void clearTaskSetSuccess() {
    taskSetSuccess.clear();
  }

  public void setTaskInfoToTaskSetFailed(TaskInfo taskInfo) throws InterruptedException {
    taskSetFailed.put(taskInfo);
  }

  public void clearTaskSetFailed() {
    taskSetFailed.clear();
  }

  public synchronized void setTaskToPrepareToRun(String executorId, TaskInfo taskInfo) {
    prepareToRun.put(executorId, taskInfo);
  }

  public synchronized TaskInfo getTaskFromPrepareToRun(String executorId) {
    return prepareToRun.get(executorId);
  }





}
