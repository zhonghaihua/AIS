package org.tencent.ais.data.util;


import org.junit.Test;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by iwardzhong on 2017/2/20.
 */
public class TestConfUtils {

  private Test2 test2 = new Test2(this);

  public static void main(String argv[]) throws Exception {
    System.out.println(ConfUtils.getConf().get("database.pass"));
    System.out.println(System.getProperty("user.dir"));
//    Date date = new Date(System.currentTimeMillis());
//    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    System.out.println(date.toString());
//    System.out.println(date.getDate());
//    System.out.println(date.getHours());
  }

  @Test
  public void printInfo() {
    test2.print2();
  }

  public void printInfo2() {
    System.out.println("bbbbbbb");
  }
}
