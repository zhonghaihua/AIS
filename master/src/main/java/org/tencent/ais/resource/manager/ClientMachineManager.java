package org.tencent.ais.resource.manager;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by iwardzhong on 2017/2/23.
 */
public class ClientMachineManager {
  private Map<Integer, List<String>> platformToClientMachineList = new HashMap<>();
  private final ReentrantLock putLock = new ReentrantLock();
  private static ClientMachineManager clientMachineManagerInstance = null;

  private ClientMachineManager() {
  }

  public static ClientMachineManager getInstance() {
    if (clientMachineManagerInstance == null) {
      synchronized (ClientMachineManager.class) {
        clientMachineManagerInstance = new ClientMachineManager();
        clientMachineManagerInstance.initialize();
      }
    }
    return clientMachineManagerInstance;
  }

  public List<String> getMachineListToPlatform(int platformId) {
    try {
      this.putLock.lockInterruptibly();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return platformToClientMachineList.get(platformId);
  }

  public String getMachineToPlatformByRandom(int platformId) {
    try {
      this.putLock.lockInterruptibly();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    List<String> machineList = platformToClientMachineList.get(platformId);
    Random r = new Random(5);
    int index = r.nextInt(machineList.size());
    return machineList.get(index);
  }

  public String getTheBestMachineToPlatform(int platformId) {
    try {
      this.putLock.lockInterruptibly();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // TODO find the best client
    return null;
  }

  public void updatePlatformToClientMachineList(
          int platformId, List<String> machineList) {
    try {
      this.putLock.lockInterruptibly();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    platformToClientMachineList.put(platformId, machineList);
  }

  public void initialize() {
    List<String> machineList = new ArrayList<>();
    machineList.add("172.27.17.12");
    machineList.add("172.26.17.12");
    machineList.add("172.25.17.12");
    updatePlatformToClientMachineList(5, machineList);

  }



}
