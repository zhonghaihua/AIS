package org.tencent.ais.resource;

/**
 * Created by iwardzhong on 2017/2/23.
 */
public class ResourceInfo {
  public int totalcpu;
  public int freecpu;
  public int busycpu;
  public int totalmemory;
  public int freememory;
  public int platformFreecpu;
  public int platformFreememory;

  public int getTotalcpu() {
    return totalcpu;
  }

  public void setTotalcpu(int totalcpu) {
    this.totalcpu = totalcpu;
  }

  public int getFreecpu() {
    return freecpu;
  }

  public void setFreecpu(int freecpu) {
    this.freecpu = freecpu;
  }

  public int getBusycpu() {
    return busycpu;
  }

  public void setBusycpu(int busycpu) {
    this.busycpu = busycpu;
  }

  public int getTotalmemory() {
    return totalmemory;
  }

  public void setTotalmemory(int totalmemory) {
    this.totalmemory = totalmemory;
  }

  public int getFreememory() {
    return freememory;
  }

  public void setFreememory(int freememory) {
    this.freememory = freememory;
  }

  public int getPlatformFreecpu() {
    return platformFreecpu;
  }

  public void setPlatformFreecpu(int platformFreecpu) {
    this.platformFreecpu = platformFreecpu;
  }

  public int getPlatformFreememory() {
    return platformFreememory;
  }

  public void setPlatformFreememory(int platformFreememory) {
    this.platformFreememory = platformFreememory;
  }
}
