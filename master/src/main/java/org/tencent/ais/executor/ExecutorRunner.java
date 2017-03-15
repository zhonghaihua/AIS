package org.tencent.ais.executor;

import org.tencent.ais.data.util.CommonConf;
import org.tencent.ais.util.AISUtils;

import java.io.IOException;

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
    // TODO 准备基础环境 这个暂时不需要做什么，是为了以后准备的
    return null;
  }

  public String prepareCommand() {
    String exeCmd = "'cd /data/iward/ais/bin/ && sh start-ais-client -platform " + platformId +
            " -executorId " + executorId + " > executor_" + executorId + ".log 2>&1 '";
    String cmd = "ssh root@" + clientIp + " " + exeCmd ;

    return cmd;
  }

  public void startExecutor() {
    System.out.println("start executor");
    String cmd = prepareCommand();
    System.out.println("exec cmd: " + cmd);
    try {
      String res = AISUtils.asynExecuShellCmd(executorId, cmd);
      System.out.println("cmd res: " + res);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

//  public static void main(String argv[]) throws Exception {
//
//  }
}
