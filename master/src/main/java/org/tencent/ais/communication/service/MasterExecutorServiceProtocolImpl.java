package org.tencent.ais.communication.service;

import org.apache.thrift.TException;
import org.tencent.ais.communication.protocol.ExecutorMessage;
import org.tencent.ais.communication.protocol.MasterExecutorServiceProtocol;
import org.tencent.ais.communication.protocol.TaskEvent;
import org.tencent.ais.communication.protocol.TaskMessage;
import org.tencent.ais.communication.util.ConvertUtil;
import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.executor.manager.ExecutorManager;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.manager.TaskSetManager;
import org.tencent.ais.task.scheduler.TaskScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by iwardzhong on 2017/3/6.
 */
public class MasterExecutorServiceProtocolImpl implements MasterExecutorServiceProtocol.Iface {

  @Override
  public TaskMessage launchTaskToRun(String executorId) throws TException {
    System.out.println("handle now");
    TaskSetManager taskSetManager = TaskSetManager.getTaskSetManagerInstance();
    TaskInfo taskInfo = taskSetManager.getTaskFromPrepareToRun(executorId);
    TaskMessage taskMessage = ConvertUtil.taskInfoToTaskMessage(taskInfo);
    System.out.println(taskMessage.getAlgoId());
    return taskMessage;
  }

  @Override
  public Map<String, String> reportHeartbeat(ExecutorMessage executorMessage) throws TException {
    ExecutorInfo executorInfo = ConvertUtil.executorMessageToExecutorInfo(executorMessage);
    Map<String, String> res = ExecutorManager.getInstance().handleExecutorHeartbeat(executorInfo);
    System.out.println("executor hostname is: " + executorMessage.getExecutorHostname());
    return res;
  }

  @Override
  public String getTaskStatusByTaskId(String taskId) throws TException {
    return "zhonghaihua";
  }

  @Override
  public List<String> getTaskListByClientIp(String clientIp) throws TException {
    List<String> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    return list;
  }

  @Override
  public boolean updateTaskEvent(TaskEvent taskEvent, String clientIp) throws TException {
    Event event = ConvertUtil.taskEventToEvent(taskEvent, clientIp);
    try {
      TaskScheduler.getTaskSchedulerInstance().getEventProcessLoop().post(event);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public boolean sendTaskPidAndExecutorPid(String taskPid, String executorId, String executorPid) throws TException {
    ExecutorManager.getInstance().setExecutorIdToTaskPidAndExecutorPid(executorId, taskPid, executorPid);
    System.out.println("taskPid: " + taskPid + " executorPid: " + executorPid);
    return true;
  }

  @Override
  public boolean executorRelease(String executorId, String clientIp) throws TException {
    TaskScheduler.getTaskSchedulerInstance().releaseExecutorFromClient(executorId, clientIp);
    return true;
  }


}
