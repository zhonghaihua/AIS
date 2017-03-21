package org.tencent.ais.communication.client;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.tencent.ais.executor.ExecutorInfo;
import org.tencent.ais.resource.ResourceInfo;
import org.tencent.ais.util.SystemInfoUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

  @Test
  public void testgen() {
    List<String> machinelist = new ArrayList<>();
    machinelist.add("10.10.11.11");
    machinelist.add("10.121.21.1");
    machinelist.add("12.231.31.1");
    String machinestr = "";
    for (String machine: machinelist) {
      machinestr = machinestr + machine + "\\n";
    }
    System.out.println(machinestr);
    System.out.println(machinestr.substring(0, machinestr.length()-2));

    String workdir = "/data/workdir/data_tmp/" + "1" + "/" + "1212";
    String genMachineFilecmd = "echo -e '" + machinestr + "' > " + workdir + "/machineFile";
    System.out.println(genMachineFilecmd);
  }

  @Test
  public void testGetList() {
    List<String> mpilist = new ArrayList<>();
    String path = "D:\\github_project\\myHadoop\\AIS\\master\\src\\main\\java\\org\\tencent\\ais\\data\\util\\mpiClusterList";
    try {
      InputStream input = new FileInputStream(path);
      mpilist = IOUtils.readLines(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(mpilist.size());
  }

}
