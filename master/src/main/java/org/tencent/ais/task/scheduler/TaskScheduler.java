package org.tencent.ais.task.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.data.manager.TaskData;
import org.tencent.ais.data.manager.TaskDataManager;
import org.tencent.ais.executor.*;
import org.tencent.ais.executor.manager.ExecutorManager;
import org.tencent.ais.resource.manager.ClientMachineManager;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.event.RunTaskEvent;
import org.tencent.ais.task.event.UpdateTaskEvent;
import org.tencent.ais.task.manager.TaskSetManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by iwardzhong on 2017/2/22.
 */
public class TaskScheduler {

  private Logger log = LoggerFactory.getLogger("TaskScheduler");
  private TaskSetManager taskSetManager;
  private TaskDataManager taskDataManager;
  private EventLoop eventProcessLoop = new TaskSchedulerEventProcessLoop(this);
  private int automaticExecutorId = 0;
  private Map<String, String> clientToExecutor = new ConcurrentHashMap<>();
  private static TaskScheduler taskSchedulerInstance = null;

  private TaskScheduler(TaskSetManager taskSetManager, TaskDataManager taskDataManager) {
    this.taskSetManager = taskSetManager;
    this.taskDataManager = taskDataManager;
  }

  public static TaskScheduler getInstance(TaskSetManager taskSetManager, TaskDataManager taskDataManager) {
    if (taskSchedulerInstance == null) {
      synchronized (TaskScheduler.class) {
        taskSchedulerInstance = new TaskScheduler(taskSetManager, taskDataManager);
      }
    }
    return taskSchedulerInstance;
  }

  /**
   * 其实这不符合单例原则，但是这个类的单例模式有点特殊，除了在master初始化的时候，在其它地方很难实现共用，
   * 所以特别开了一个口，提供给其它地方调用，但是要保证这个方法调用是在getInstance方法之后，否则会出异常
   * @return
   */
  public static TaskScheduler getTaskSchedulerInstance() {
    return taskSchedulerInstance;
  }

  public void handleTaskToRun(Event event) {
    System.out.println("start to handle");
    int platformId = event.getTaskInfo().getPlatform();
    System.out.println(platformId);
    String executorHostname = ClientMachineManager.getInstance().getMachineToPlatformByRandom(platformId);
    System.out.println(executorHostname);
    System.out.println("hello");
    String executorId;
    if (clientToExecutor.keySet().contains(executorHostname)) {
      executorId = clientToExecutor.get(executorHostname);
    } else {
      executorId = String.valueOf(getAutomaticExecutorId());
    }
    ExecutorManager.getInstance().setExecutorIdToTaskInfo(executorId, event.getTaskInfo());
    taskSetManager.setTaskToPrepareToRun(executorId, event.getTaskInfo()); // Executor启动完成后会从这个集合主动拉取
    // TODO 创建一个ExecutorRunner，然后传入参数去远程启动一个exeuctor, 如果远程存在进程就直接launchTask
    if (clientToExecutor.keySet().contains(executorHostname)) {
      // 通知executor launch the task,在下次心跳过来时通知
      ExecutorManager.getInstance().setExecutorLaunchTaskInHeartbeat(executorId);
    } else {
      new ExecutorRunner(executorId, String.valueOf(platformId), executorHostname).startExecutor();
      clientToExecutor.put(executorHostname, executorId);
      ExecutorManager.getInstance().addExecutorIdToIp(executorId, executorHostname);
      ExecutorManager.getInstance().setExecutorLastseen(executorId, System.currentTimeMillis());
    }

    System.out.println("event runing" + event.getTaskInfo().getTaskData().getAlgoId());
    if (event.getTaskInfo().getTaskData().getDataType() == 0) {
      log.info("Access task: " + event.getTaskInfo().getTaskData().getAccessId() +
              "has been put to prepareToRun queue");
    } else {
      log.info("Task: " + event.getTaskInfo().getTaskData().getTaskId() +
              "has been put to prepareToRun queue");
    }
  }

  public void handleRunningTaskToComplete(Event event) {
    TaskInfo taskInfo = event.getTaskInfo();
    TaskData taskData = taskInfo.getTaskData();
    taskData.setRate(100);
    taskData.setStatus(2);
    updateTask(taskData);
    if (taskData.getDataType() == 0 ) {
      log.info("access task is finished, access id is " + taskData.getAccessId());
    } else {
      log.info("task is finished, task id is " + taskData.getTaskId());
    }
    try {
      taskSetManager.setTaskInfoToTaskSetSuccess(taskInfo);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void handleRunningTaskToFailed(Event event) {
    TaskInfo taskInfo = event.getTaskInfo();
    TaskData taskData = taskInfo.getTaskData();
    if (taskData.getDataType() == 0) {
      taskData.setStatus(3);
      updateTask(taskData);
      log.error("access task is failed, access id is " + taskData.getAccessId());
    } else {
      taskData.setStatus(3);
      taskData.setRate(100);
      updateTask(taskData);
      log.error("task is failed, task id is " + taskData.getTaskId());
    }
    try {
      taskSetManager.setTaskInfoToTaskSetFailed(taskInfo);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void handleUpdateTaskInfo(Event event) {
    TaskInfo taskInfo = event.getTaskInfo();
    TaskData taskData = taskInfo.getTaskData();
    updateTask(taskData);
  }

  /**
   * 将taskinfo包装成event，放到事件队列
   * @param taskInfo
   */
  public void runTask(TaskInfo taskInfo) throws InterruptedException {
    taskInfo.getTaskData().setStatus(1);
    UpdateTaskEvent updateTaskEvent = new UpdateTaskEvent(taskInfo);
    eventProcessLoop.post(updateTaskEvent);
    RunTaskEvent runTaskEvent = new RunTaskEvent(taskInfo);
    eventProcessLoop.post(runTaskEvent);
  }

  private boolean updateTask(TaskData taskData) {
    boolean res;
    if (taskData.getDataType() == 0) {
      res = taskDataManager.updateAccessTaskStatusByTaskId(
              taskData.getAccessId(), taskData.getStatus(), taskData.getRate(),
              taskData.getStartTime(), taskData.getEndTime());
      if (!res) {
        log.error("update access task info failed, access id is " + taskData.getAccessId());
      }
    } else {
      res = taskDataManager.updateTaskStatusByTaskId(taskData.getTaskId(),
              taskData.getStatus(), taskData.getRate(), "''",
              taskData.getStartTime(), taskData.getEndTime());
      if (!res) {
        log.error("update task info failed, task id is " + taskData.getTaskId());
      }
    }
    return res;
  }

  public void initialize() {
    System.out.println("taskscheduler run");
    eventProcessLoop.start();
  }

  private synchronized int getAutomaticExecutorId() {
    automaticExecutorId += 1;
    return automaticExecutorId;
  }

  public EventLoop getEventProcessLoop() {
    return eventProcessLoop;
  }

//  private Executor getExecutor(int platformId, String executorId, String executorHostname, TaskInfo taskInfo) {
//    if (platformId == 1) {
//      return new TFExecutor(executorId, executorHostname);
//    } else if (platformId == 2) {
//      return new XgboostExecutor(executorId, executorHostname);
//    } else if (platformId == 3) {
//      return new MPIExecutor(executorId, executorHostname);
//    } else {
//      return new SparkExecutor(executorId, executorHostname);
//    }
//  }

}
