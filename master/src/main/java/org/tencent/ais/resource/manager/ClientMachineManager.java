package org.tencent.ais.resource.manager;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by iwardzhong on 2017/2/23.
 */
public class ClientMachineManager {
  private Map<Integer, List<String>> platformToClientMachineList = new HashMap<>();
  private Set<String> mpiClusterTotalMachineSet = new HashSet<>();
  private Set<String> mpiClusterBusyMachineSet = new HashSet<>();
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
      return platformToClientMachineList.get(platformId);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.putLock.unlock();
    }
    return null;
  }

  public String getMachineToPlatformByRandom(int platformId) {
    try {
      this.putLock.lockInterruptibly();
      List<String> machineList = platformToClientMachineList.get(platformId);
      Random r = new Random(5);
      int index = r.nextInt(machineList.size());
      return machineList.get(index);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.putLock.unlock();
    }
    return  null;
  }

  public String getTheBestMachineToPlatform(int platformId) {
    try {
      this.putLock.lockInterruptibly();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.putLock.unlock();
    }
    // TODO find the best client
    return null;
  }

  public void updatePlatformToClientMachineList(
          int platformId, List<String> machineList) {
    try {
      this.putLock.lockInterruptibly();
      platformToClientMachineList.put(platformId, machineList);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      this.putLock.unlock();
    }
  }

  public synchronized void setMpiClusterBusyMachineSet(Set<String> list) {
    mpiClusterBusyMachineSet.addAll(list);
  }

  public synchronized void freeMachineFormBusy(Set<String> list) {
    mpiClusterBusyMachineSet.removeAll(list);
  }

  public Set<String> getMpiClusterBusyMachineSet() {
    return mpiClusterBusyMachineSet;
  }

  public Set<String> getMpiClusterTotalMachineSet() {
    return mpiClusterTotalMachineSet;
  }

  public Set<String> getFreeMachline() {
    Set<String> freeSet = new HashSet<>();
    freeSet.addAll(mpiClusterTotalMachineSet);
    freeSet.removeAll(mpiClusterBusyMachineSet);
    return freeSet;
  }

  public void initialize() {
    List<String> machineList = new ArrayList<>();
    machineList.add("10.151.15.155");
    machineList.add("10.151.15.155");
    machineList.add("10.151.15.155");
    updatePlatformToClientMachineList(5, machineList);
    updatePlatformToClientMachineList(3, machineList);
    mpiClusterTotalMachineSet.add("10.51.212.42");
    mpiClusterTotalMachineSet.add("10.51.212.43");
    mpiClusterTotalMachineSet.add("10.51.212.44");
  }



}
