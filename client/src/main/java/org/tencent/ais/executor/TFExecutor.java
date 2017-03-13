package org.tencent.ais.executor;

import org.tencent.ais.task.TaskInfo;

/**
 * Created by iwardzhong on 2017/3/3.
 */
public class TFExecutor extends Executor{

  public TFExecutor(String executorId, String executorHostname) {
    super(executorId, executorHostname);
  }

  @Override
  protected void run(ExecutorInfo executorInfo) {

  }

  @Override
  public void launchTask() {

  }

  @Override
  public void reportHeartbeat(ExecutorInfo executorInfo) {

  }

  @Override
  public void killTask() {

  }

  @Override
  public void stop() {

  }
}
