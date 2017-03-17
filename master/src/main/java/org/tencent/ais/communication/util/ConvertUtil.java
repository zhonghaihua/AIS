package org.tencent.ais.communication.util;


import org.tencent.ais.communication.protocol.ExecutorMessage;
import org.tencent.ais.communication.protocol.ResourceMessage;
import org.tencent.ais.communication.protocol.TaskEvent;
import org.tencent.ais.communication.protocol.TaskMessage;
import org.tencent.ais.data.manager.TaskData;
import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.CompleteTaskEvent;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.event.FailedTaskEvent;
import org.tencent.ais.task.event.UpdateTaskEvent;

/**
 * Created by iwardzhong on 2017/3/1.
 */
public class ConvertUtil {

  public static TaskMessage taskInfoToTaskMessage(TaskInfo taskInfo) {
    TaskMessage taskMessage = new TaskMessage();
    TaskData taskData = taskInfo.getTaskData();

    taskMessage.setDataType(taskData.getDataType());
    taskMessage.setTaskId(taskData.getTaskId());
    taskMessage.setAlgoId(taskData.getAlgoId());
    taskMessage.setPlatformId(taskData.getPlatformId());
    taskMessage.setInputFormat(taskData.getInputFormat());
    taskMessage.setInputPath(taskData.getInputPath());
    taskMessage.setOutputPath(taskData.getOutputPath());
    taskMessage.setParameterPath(taskData.getParameterPath());
    taskMessage.setPackagePath(taskData.getPackagePath());
    taskMessage.setTaskType(taskData.getTaskType());
    taskMessage.setSubmitTime(taskData.getSubmitTime());
    taskMessage.setStartTime(taskData.getStartTime());
    taskMessage.setEndTime(taskData.getEndTime());
    taskMessage.setModifyTime(taskData.getModifyTime());
    taskMessage.setStatus(taskData.getStatus());
    taskMessage.setRate(taskData.getRate());
    taskMessage.setErrMsg(taskData.getErrMsg());
    taskMessage.setUserId(taskData.getUserId());
    taskMessage.setTaskInfo(taskData.getTaskInfo());
    taskMessage.setAccessId(taskData.getAccessId());
    taskMessage.setAlgoName(taskData.getAlgoName());
    taskMessage.setAccessStatus(taskData.getAccessStatus());
    taskMessage.setAccessProgress(taskData.getAccessProgress());
    taskMessage.setAlgoDescription(taskData.getAlgoDescription());
    taskMessage.setMpimachineList(taskInfo.getMachineList());

    return taskMessage;
  }

  public static TaskInfo taskMessageToTaskInfo(TaskMessage taskMessage, String executorHostname) {
    TaskInfo taskInfo = new TaskInfo();
    TaskData  taskData = new TaskData();

    taskData.setDataType(taskMessage.getDataType());
    taskData.setTaskId(taskMessage.getTaskId());
    taskData.setAlgoId(taskMessage.getAlgoId());
    taskData.setPlatformId(taskMessage.getPlatformId());
    taskData.setInputFormat(taskMessage.getInputFormat());
    taskData.setInputPath(taskMessage.getInputPath());
    taskData.setOutputPath(taskMessage.getOutputPath());
    taskData.setParameterPath(taskMessage.getParameterPath());
    taskData.setPackagePath(taskMessage.getPackagePath());
    taskData.setTaskType(taskMessage.getTaskType());
    taskData.setSubmitTime(taskMessage.getSubmitTime());
    taskData.setStartTime(taskMessage.getStartTime());
    taskData.setEndTime(taskMessage.getEndTime());
    taskData.setModifyTime(taskMessage.getModifyTime());
    taskData.setStatus(taskMessage.getStatus());
    taskData.setRate(taskMessage.getRate());
    taskData.setErrMsg(taskMessage.getErrMsg());
    taskData.setUserId(taskMessage.getUserId());
    taskData.setTaskInfo(taskMessage.getTaskInfo());
    taskData.setAccessId(taskMessage.getAccessId());
    taskData.setAlgoName(taskMessage.getAlgoName());
    taskData.setAccessStatus(taskMessage.getAccessStatus());
    taskData.setAccessProgress(taskMessage.getAccessProgress());
    taskData.setAlgoDescription(taskMessage.getAlgoDescription());

    taskInfo.setMachineList(taskMessage.getMpimachineList());
    taskInfo.setPlatform(taskData.getPlatformId());
    taskInfo.setTaskClientIp(executorHostname);
    taskInfo.setTaskData(taskData);
    return taskInfo;
  }

  public static ResourceInfo resourceMessageToResourceInfo(ResourceMessage resourceMessage) {
    ResourceInfo resourceInfo = new ResourceInfo();
    resourceInfo.setTotalcpu(resourceMessage.getTotalcpu());
    resourceInfo.setFreecpu(resourceMessage.getFreecpu());
    resourceInfo.setBusycpu(resourceMessage.getBusycpu());
    resourceInfo.setTotalmemory(resourceMessage.getTotalmemory());
    resourceInfo.setFreememory(resourceMessage.getFreememory());
    resourceInfo.setPlatformFreecpu(resourceMessage.getPlatformFreecpu());
    resourceInfo.setPlatformFreememory(resourceMessage.getPlatformFreememory());

    return resourceInfo;
  }

  public static ResourceMessage resourceInfoToResourceMessage(ResourceInfo resourceInfo) {
    ResourceMessage resourceMessage = new ResourceMessage();
    resourceMessage.setTotalcpu(resourceInfo.getTotalcpu());
    resourceMessage.setFreecpu(resourceInfo.getFreecpu());
    resourceMessage.setBusycpu(resourceInfo.getBusycpu());
    resourceMessage.setTotalmemory(resourceInfo.getTotalmemory());
    resourceMessage.setFreememory(resourceInfo.getFreememory());
    resourceMessage.setPlatformFreecpu(resourceInfo.getPlatformFreecpu());
    resourceMessage.setPlatformFreememory(resourceInfo.getPlatformFreememory());
    return resourceMessage;
  }

  public static ExecutorInfo executorMessageToExecutorInfo(ExecutorMessage executorMessage) {
    ExecutorInfo executorInfo = new ExecutorInfo();
    ResourceInfo resourceInfo = resourceMessageToResourceInfo(executorMessage.getResourceMessage());
    executorInfo.setExecutorResourceInfo(resourceInfo);
    executorInfo.setExecutorHostname(executorMessage.getExecutorHostname());
    executorInfo.setExecutorId(executorMessage.getExecutorId());
    executorInfo.setTaskId(executorMessage.getTaskId());
    executorInfo.setLastUpdate(executorMessage.getLastUpdate());
    return executorInfo;
  }

  public static ExecutorMessage executorInfoToExecutorMessage(ExecutorInfo executorInfo) {
    ExecutorMessage executorMessage = new ExecutorMessage();
    ResourceMessage resourceMessage = resourceInfoToResourceMessage(executorInfo.getExecutorResourceInfo());
    executorMessage.setExecutorHostname(executorInfo.getExecutorHostname());
    executorMessage.setExecutorId(executorInfo.getExecutorId());
    executorMessage.setTaskId(executorInfo.getTaskId());
    executorMessage.setResourceMessage(resourceMessage);
    executorMessage.setLastUpdate(executorInfo.getLastUpdate());
    return executorMessage;
  }

  public static Event taskEventToEvent(TaskEvent taskEvent, String clientIp) {
    TaskInfo taskInfo = taskMessageToTaskInfo(taskEvent.getTaskMessage(), clientIp);
    String type = taskEvent.getEventType().toLowerCase();
    if (type.contains("update")) {
      return new UpdateTaskEvent(taskInfo);
    } else if (type.contains("failed")) {
      return new FailedTaskEvent(taskInfo);
    } else if (type.contains("complete")) {
      return new CompleteTaskEvent(taskInfo);
    } else {
      return null;
    }
  }

  public static TaskEvent eventToTaskEvent(Event event) {
    TaskMessage taskMessage = taskInfoToTaskMessage(event.getTaskInfo());
    String type = "";
    if (event instanceof CompleteTaskEvent) {
      type = "complete";
    } else if (event instanceof FailedTaskEvent) {
      type = "failed";
    } else if (event instanceof UpdateTaskEvent) {
      type = "update";
    }
    return new TaskEvent(type, taskMessage);
  }

}
