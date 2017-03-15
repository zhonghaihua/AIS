package org.tencent.ais.communication.client;

import org.junit.Test;
import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.util.SystemInfoUtils;

import java.util.Map;

/**
 * Created by iwardzhong on 2017/3/6.
 */
public class TestClient {

  @Test
  public void testServiceClient() {
    MasterExecutorServiceProtocolClient client = new MasterExecutorServiceProtocolClient();
    ResourceInfo resourceInfo = new ResourceInfo();
    resourceInfo.setTotalcpu(100);
    resourceInfo.setFreememory(12);
    resourceInfo.setPlatformFreememory(1212);
    resourceInfo.setPlatformFreecpu(1212);
    resourceInfo.setTotalmemory(121212);
    resourceInfo.setBusycpu(12);
    resourceInfo.setFreecpu(12);
    ExecutorInfo executorInfo = new ExecutorInfo();
    executorInfo.setExecutorId("12");
    executorInfo.setExecutorResourceInfo(resourceInfo);
    executorInfo.setTaskId("12");
    executorInfo.setLastUpdate(3000L);
    executorInfo.setExecutorHostname("10.27.11.148");
    Map<String, String> result = client.reportHeartbeat(executorInfo);
    System.out.println("result: " + result);
  }

  @Test
  public void testGetIp() {
    System.out.println(SystemInfoUtils.getLocalHostIp());
  }

}
