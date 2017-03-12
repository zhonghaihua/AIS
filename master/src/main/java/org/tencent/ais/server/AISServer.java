package org.tencent.ais.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.data.util.CommonConf;

/**
 * Created by iwardzhong on 2017/3/2.
 */
public class AISServer {
  public static volatile boolean running = true;
  private Logger log = LoggerFactory.getLogger("AISServer");
  private AISThriftServer aisThriftServer;

  public synchronized void join() throws InterruptedException {
    while (CommonConf.running) {
      wait();
    }
  }

  private AISServer() {
    aisThriftServer = new AISThriftServer();
  }

  public static AISServer createServer() {
    return new AISServer();
  }

  public void start() {
    log.info("start ais server");
    System.out.println("start ais server");
    aisThriftServer.startServer();
  }

  public synchronized void stop() {
    log.info("stop ais server");
    System.out.println("stop ais server");
    aisThriftServer.stopServer();
    notifyAll();
  }
}
