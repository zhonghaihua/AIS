package org.tencent.ais.executor;

import org.tencent.ais.data.util.CommonConf;

/**
 * Created by iwardzhong on 2017/3/1.
 */
public class ExecutorRunner {
  private String executorId;
  private String platformId;
  private String clientIp;
  private String memory = CommonConf.EXECUTOR_MEMORY;

  public ExecutorRunner(String executorId, String platformId, String clientIp) {
    this.executorId = executorId;
    this.platformId = platformId;
    this.clientIp = clientIp;
  }

  public String prepareEnvironment() {
    // TODO 准备基础环境
    return null;
  }

  public String prepareCommand() {
    // TODO 拼接执行命令
    return null;
  }

  public void startExecutor() {
    // TODO 远程执行命令
  }

  public static void main(String argv[]) throws Exception {

  }
}
