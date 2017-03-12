package org.tencent.ais.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.communication.protocol.MasterExecutorServiceProtocol;
import org.tencent.ais.communication.service.MasterExecutorServiceProtocolImpl;
import org.tencent.ais.data.util.CommonConf;

/**
 * Created by iwardzhong on 2017/3/6.
 */
public class AISThriftServer {
  private static int server_port = CommonConf.SERVER_PORT;
  private Logger log = LoggerFactory.getLogger("AISThriftServer");
  private TServer masterExecutorServiceProtocolServer;

  public void startServer() {
    log.info("start ais thrift server");
    System.out.println("start ais thrift server");
    startMasterExecutorServiceProtocolServer();
  }

  public void stopServer() {
    log.info("stop ais thrift server");
    System.out.println("stop ais thrift server");
    stopMasterExecutorServiceProtocolServer();
  }

  private void startMasterExecutorServiceProtocolServer() {
    try {
      log.info("start MasterExecutorServiceProtocol server");
      System.out.println("start MasterExecutorServiceProtocol server");
      TProcessor tProcessor = new MasterExecutorServiceProtocol.Processor
              <MasterExecutorServiceProtocol.Iface>(new MasterExecutorServiceProtocolImpl());
      TServerSocket tServerSocket = new TServerSocket(server_port);
      TThreadPoolServer.Args tthpsArgs = new TThreadPoolServer.Args(tServerSocket);
      tthpsArgs.processor(tProcessor);
      tthpsArgs.protocolFactory(new TCompactProtocol.Factory());
      masterExecutorServiceProtocolServer = new TThreadPoolServer(tthpsArgs);
      masterExecutorServiceProtocolServer.serve();
    } catch (TTransportException e) {
      e.printStackTrace();
    }
  }

  private void stopMasterExecutorServiceProtocolServer() {
    masterExecutorServiceProtocolServer.stop();
  }
}
