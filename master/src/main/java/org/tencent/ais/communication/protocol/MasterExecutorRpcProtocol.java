package org.tencent.ais.communication.protocol;

import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.event.RunTaskEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by iwardzhong on 2017/2/17.
 */
public interface MasterExecutorRpcProtocol {

  // TODO client先启动exeuctor服务，然后再调用launchTaskToRun加载task信息去运行
  public TaskInfo launchTaskToRun(ExecutorInfo executorInfo);

  // TODO 汇报心跳，返回信息
  public Map<String, String> reportHeartbeat(ExecutorInfo executorInfo);

  // TODO 根据taskId返回task的状态
  public String getTaskStatusByTaskId(String taskId);

  // TODO 根据client ip 返回客户端提交过的task
  public List<String> getTaskListByClientIp(String clientIp);

  // TODO 更新任务事件类型
  public boolean updateTaskEvent(Event event, String clientIp);

}
