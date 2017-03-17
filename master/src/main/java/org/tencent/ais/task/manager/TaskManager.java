package org.tencent.ais.task.manager;

import org.tencent.ais.data.manager.TaskData;
import org.tencent.ais.data.manager.TaskDataManager;
import org.tencent.ais.data.util.CommonConf;
import org.tencent.ais.resource.manager.ClientMachineManager;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.UpdateTaskEvent;
import org.tencent.ais.task.scheduler.TaskScheduler;

import java.util.List;

/**
 * Created by iwardzhong on 2017/2/22.
 */
public class TaskManager {
  private TaskDataManager taskDataManager;
  private TaskSetManager taskSetManager;
  private TaskScheduler taskScheduler;

  public TaskManager(TaskDataManager tdm, TaskSetManager tsm, TaskScheduler ts) {
    this.taskDataManager = tdm;
    this.taskSetManager = tsm;
    this.taskScheduler = ts;
  }

  private Thread initialTaskToRunningQueueThread = new Thread(new Runnable() {
    @Override
    public void run() {
      while (CommonConf.running) {
        System.out.println("begin get task data");
        try {
          // 暂时只获取mpi集群的
          List<TaskData> taskDataList = taskDataManager.getTaskByPlatformIdFromDB(3, 0, true);
          for (TaskData taskData : taskDataList) {
            String taskClientIp = ClientMachineManager.getInstance().getMachineToPlatformByRandom(taskData.getPlatformId());
            TaskInfo taskInfo = taskDataWrapToTaskInfo(taskData, taskClientIp);
            taskSetManager.setTaskInfoToTaskSetToRun(taskInfo);
          }
          Thread.sleep(5000);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  });

  private Thread initialAccessTaskToRunningQueueThread = new Thread(new Runnable() {
    @Override
    public void run() {
      while (CommonConf.running) {
        System.out.println("begin get access data");
        try {
          List<TaskData> taskDataList = taskDataManager.getAccessTaskByPlatformIdFromDB(0, 0, false);
          System.out.println("access task list size" + taskDataList.size());
          for (TaskData taskData: taskDataList) {
            String taskClientIp = ClientMachineManager.getInstance().getMachineToPlatformByRandom(taskData.getPlatformId());
            TaskInfo taskInfo = taskDataWrapToTaskInfo(taskData, taskClientIp);
            taskSetManager.setTaskInfoToTaskSetToRun(taskInfo);
          }
          Thread.sleep(5000);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  });

  private TaskInfo taskDataWrapToTaskInfo(TaskData taskData, String taskClientIp) {
    TaskInfo taskInfo = new TaskInfo();
    taskInfo.setTaskData(taskData);
    taskInfo.setTaskClientIp(taskClientIp);
    taskInfo.setPlatform(taskData.getPlatformId());
    return taskInfo;
  }

  private Thread taskToEventQueueThread = new Thread(new Runnable() {
    @Override
    public void run() {
      while (CommonConf.running) {
        TaskInfo taskInfo = taskSetManager.getTaskInfoFromTaskSetToRun();
        if (taskInfo != null) {
          try {
            taskScheduler.runTask(taskInfo);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  });

  public void initialize() {
    System.out.println("taskmanager run");
//    initialAccessTaskToRunningQueueThread.setDaemon(true);
//    initialTaskToRunningQueueThread.setDaemon(true);
//    taskToEventQueueThread.setDaemon(true);
    initialAccessTaskToRunningQueueThread.start();
    initialTaskToRunningQueueThread.start();
    taskToEventQueueThread.start();
  }

}
