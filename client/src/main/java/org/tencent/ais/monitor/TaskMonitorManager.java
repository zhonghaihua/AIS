package org.tencent.ais.monitor;

import org.tencent.ais.communication.client.MasterExecutorServiceProtocolClient;
import org.tencent.ais.data.util.DBUtils;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.CompleteTaskEvent;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.event.FailedTaskEvent;
import org.tencent.ais.util.AISUtils;
import org.tencent.ais.util.SystemInfoUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by iwardzhong on 2017/2/17.
 */
public class TaskMonitorManager {

  private String taskPid;
  private String executorId;
  private String currenProcessPid;
  private String output_path;
  private TaskInfo taskInfo;
  private String clientIp = SystemInfoUtils.getLocalHostIp();
  private volatile boolean isRun = false;
  private MasterExecutorServiceProtocolClient mespc;
  private static TaskMonitorManager taskMonitorManagerInstance = null;
  private Thread monitorTaskThread = new Thread(new Runnable() {
    @Override
    public void run() {
      while (isRun) {
        System.out.println("start monitor");
        boolean processExist = taskProcessExist();
        boolean fileExist =  successFileExist();

        if (!processExist) {
          taskInfo.getTaskData().setRate(100);
          taskInfo.getTaskData().setAccessProgress(100);
          if (fileExist) {
            taskInfo.getTaskData().setStatus(2);
            taskInfo.getTaskData().setAccessStatus(2);
            CompleteTaskEvent completeTaskEvent = new CompleteTaskEvent(taskInfo);
            mespc.updateTaskEvent(completeTaskEvent, clientIp);
          } else {
            taskInfo.getTaskData().setStatus(3);
            taskInfo.getTaskData().setAccessStatus(3);
            FailedTaskEvent failedTaskEvent = new FailedTaskEvent(taskInfo);
            mespc.updateTaskEvent(failedTaskEvent, clientIp);
          }
          setRun(false);
        }
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  });

  private TaskMonitorManager() {}

  public static TaskMonitorManager getInstance() {
    if (taskMonitorManagerInstance == null) {
      synchronized (TaskMonitorManager.class) {
        taskMonitorManagerInstance = new TaskMonitorManager();
      }
    }
    return taskMonitorManagerInstance;
  }




  public void setTaskPid(String taskPid) {
    this.taskPid = taskPid;
  }

  public void setExecutorId(String executorId) {
    this.executorId = executorId;
  }

  public void setCurrenProcessPid(String pid) {
    this.currenProcessPid = pid;
  }

  public void setMespc(MasterExecutorServiceProtocolClient mespc) {
    this.mespc = mespc;
  }

  public boolean isRun() {
    return isRun;
  }

  public void setRun(boolean run) {
    isRun = run;
  }

  public void setOutput_path(String path) {
    output_path = path;
  }

  public void setTaskInfo(TaskInfo taskInfo) {
    this.taskInfo = taskInfo;
  }

  public void sendTaskPidAndExecutorPid() {
    mespc.sendTaskPidAndExecutorPid(taskPid, executorId, currenProcessPid);
  }

  public void killTask() {
    String cmd = "kill -9 " + taskPid;
    try {
      AISUtils.execuShellCmd(cmd);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean taskProcessExist() {
    boolean r = false;
    int taskId;
    if (taskInfo.getTaskData().getDataType() == 0) {
      taskId = taskInfo.getTaskData().getAccessId();
    } else {
      taskId = taskInfo.getTaskData().getTaskId();
    }
    String cmd = "grep algo_task_ /data/workdir/data_tmp/" + executorId + "/" + taskId + "/" + taskId +".log | wc -l";
//    String cmd = "ps -ef | gerp " + taskPid + " | grep -v grep | wc -l";
    try {
      String result = AISUtils.execuShellCmd(cmd);
      if (Integer.parseInt(result) == 0) {
        r = true;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return r;
  }

  private boolean successFileExist() {
    boolean r = false;
    String success_file = output_path + "/SUCCESS";
    File file = new File(success_file);
    if (file.exists()) {
      r = true;
    }
    return r;
  }

  public void startTaskMoniterThread() {
    monitorTaskThread.start();
  }

  public static void main(String argv[]) throws Exception {
  }

}
