package org.tencent.ais.util;

import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by iwardzhong on 2017/3/6.
 */
public class SystemInfoUtils {

  public static String getLocalHostIp() {
    String ip = null;
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      ip = inetAddress.getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return ip;
  }

  public static int getTotalMemory() throws SigarException {
    Sigar sigar = new Sigar();
    Mem mem = sigar.getMem();
    int totalMemory = Integer.parseInt(String.valueOf(mem.getTotal()/(1024 * 1024 * 1024L)));
    return totalMemory;
  }

  public static int getUserMemory() throws SigarException {
    Sigar sigar = new Sigar();
    Mem mem = sigar.getMem();
    int totalMemory = Integer.parseInt(String.valueOf(mem.getActualUsed()/(1024 * 1024 * 1024L)));
    return totalMemory;
  }

  public static int getFreeMemory() throws SigarException {
    Sigar sigar = new Sigar();
    Mem mem = sigar.getMem();
    int totalMemory = Integer.parseInt(String.valueOf(mem.getActualFree()/(1024 * 1024 * 1024L)));
    return totalMemory;
  }

  private static int getTotalCpu() throws SigarException {
    Sigar sigar = new Sigar();
    CpuInfo infos[] = sigar.getCpuInfoList();
    CpuPerc cpuList[] = null;
    cpuList = sigar.getCpuPercList();
    int totalCores = 0;
    for (int i = 0; i < infos.length; i++) {
      CpuInfo info = infos[i];
      totalCores += info.getTotalCores();
    }

    return totalCores;
  }






}
