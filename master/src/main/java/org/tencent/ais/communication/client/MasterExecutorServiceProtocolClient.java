package org.tencent.ais.communication.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.tencent.ais.communication.protocol.*;
import org.tencent.ais.communication.util.ConvertUtil;
import org.tencent.ais.data.util.CommonConf;
import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.util.SystemInfoUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by iwardzhong on 2017/3/6.
 */
public class MasterExecutorServiceProtocolClient implements MasterExecutorRpcProtocol {
  private static String server_ip = CommonConf.SERVER_IP;
//  private static String server_ip = "10.27.11.59"; //test ip
  private static int server_port = CommonConf.SERVER_PORT;
  private static int server_timeout = CommonConf.SERVER_TIMEOUT;
  private MasterExecutorServiceProtocol.Client masterExecutorServiceProtocolClient;
  private TTransport masterExecutorServiceProtocolTransport = null;

  public MasterExecutorServiceProtocolClient() {
    initClient();
  }

  private void initClient() {
    initMasterExecutorServiceProtocolClient();
  }

  private void initMasterExecutorServiceProtocolClient() {
    masterExecutorServiceProtocolTransport = new TSocket(server_ip, server_port, server_timeout);
    TProtocol tProtocol = new TCompactProtocol(masterExecutorServiceProtocolTransport);
    masterExecutorServiceProtocolClient = new MasterExecutorServiceProtocol.Client(tProtocol);
  }

  @Override
  public TaskInfo launchTaskToRun(ExecutorInfo executorInfo) {
    TaskInfo taskInfo = null;
    try {
      masterExecutorServiceProtocolTransport.open();
      TaskMessage taskMessage = masterExecutorServiceProtocolClient.launchTaskToRun(executorInfo.getExecutorId());
      System.out.println(taskMessage.getAlgoId());
      String executorHostname = SystemInfoUtils.getLocalHostIp();
      taskInfo = ConvertUtil.taskMessageToTaskInfo(taskMessage, executorHostname);
    } catch (TException e) {
      e.printStackTrace();
    } finally {
      if (masterExecutorServiceProtocolTransport != null) {
        masterExecutorServiceProtocolTransport.close();
      }
    }
    return taskInfo;
  }

  @Override
  public Map<String, String> reportHeartbeat(ExecutorInfo executorInfo) {
    Map<String, String> result = null;
    try {
      masterExecutorServiceProtocolTransport.open();
      ExecutorMessage executorMessage = ConvertUtil.executorInfoToExecutorMessage(executorInfo);
      result = masterExecutorServiceProtocolClient.reportHeartbeat(executorMessage);
    } catch (TException e) {
      e.printStackTrace();
    } finally {
      if (masterExecutorServiceProtocolTransport != null) {
        masterExecutorServiceProtocolTransport.close();
      }
    }
    return result;
  }

  @Override
  public String getTaskStatusByTaskId(String taskId) {
    return null;
  }

  @Override
  public List<String> getTaskListByClientIp(String clientIp) {
    return null;
  }

  @Override
  public boolean updateTaskEvent(Event event, String clientIp) {
    boolean res = false;
    try {
      TaskEvent taskEvent = ConvertUtil.eventToTaskEvent(event);
      masterExecutorServiceProtocolTransport.open();
      res = masterExecutorServiceProtocolClient.updateTaskEvent(taskEvent, clientIp);
    } catch (TException e) {
      e.printStackTrace();
    } finally {
      if (masterExecutorServiceProtocolTransport != null) {
        masterExecutorServiceProtocolTransport.close();
      }
    }
    return res;
  }

  @Override
  public boolean sendTaskPidAndExecutorPid(String taskPid, String executorId, String executorPid) {
    boolean res = false;
    try {
      masterExecutorServiceProtocolTransport.open();
      res = masterExecutorServiceProtocolClient.sendTaskPidAndExecutorPid(taskPid, executorId, executorPid);
    } catch (TException e) {
      e.printStackTrace();
    } finally {
      if (masterExecutorServiceProtocolTransport != null) {
        masterExecutorServiceProtocolTransport.close();
      }
    }
    return res;
  }

}
