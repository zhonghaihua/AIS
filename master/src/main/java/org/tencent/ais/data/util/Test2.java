package org.tencent.ais.data.util;

/**
 * Created by iwardzhong on 2017/2/27.
 */
public class Test2 {

  TestConfUtils testConfUtils;

  public Test2(TestConfUtils testConfUtils) {
    this.testConfUtils = testConfUtils;
  }

  public void print() {
    System.out.println("aaaaaaa");
  }

  public void print2() {
    testConfUtils.printInfo2();
  }
}
