package org.tencent.ais.executor;

import org.tencent.ais.task.TaskInfo;

/**
 * Created by iwardzhong on 2017/3/3.
 */
public class XgboostExecutor extends Executor {

  public XgboostExecutor(String executorId, String executorHostname, TaskInfo taskInfo) {
    super(executorId, executorHostname, taskInfo);
  }

  @Override
  public void runTask(TaskInfo taskInfo) {

  }

  @Override
  public void reportHeartbeat() {

  }

  @Override
  public void killTask() {

  }

  @Override
  public void stop() {

  }
}
