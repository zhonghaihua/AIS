package org.tencent.ais.data.util;

import java.sql.*;


/**
 * Created by iwardzhong on 2017/2/17.
 */
public class DBUtils {

  static String database_ip = CommonConf.DATABASE_IP;
  static String database_user = CommonConf.DATABASE_USER;
  static String database_pass = CommonConf.DATABASE_PASS;
  static String database_port = CommonConf.DATABASE_PORT;
  static String database_url = "jdbc:mysql://" + database_ip + ":" + database_port + "/test?useUnicode=true&" +
          "characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
  static String className = "com.mysql.jdbc.Driver";

  Connection conn = null;
  private static DBUtils dbUtilsInstance = null;


  public static void main(String argv[]) throws Exception {
    System.out.println("hello, ais");
  }

  public void printInfo() {
    System.out.println("hi, ais");
  }

  private DBUtils() {}

  public static DBUtils getInstance() {
    if (dbUtilsInstance == null) {
      synchronized (DBUtils.class) {
        dbUtilsInstance = new DBUtils();
      }
    }
    return dbUtilsInstance;
  }

  public ResultSet execQuery(String sql) {
    ResultSet rs = null;
    try {
      conn = getConn();
      PreparedStatement sql_sm = conn.prepareStatement(sql);
      rs = sql_sm.executeQuery(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rs;
  }

  public boolean execUpdate(String sql) {
    boolean flag = false;
    try {
      conn = getConn();
      PreparedStatement sql_sm = conn.prepareStatement(sql);
      flag = sql_sm.execute(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }

  private Connection getConn() {
    if (conn == null) {
      conn = createConn();
    }
    return conn;
  }

  private Connection createConn() {
    try {
      Class.forName(className);
      System.out.println(database_url + " " + database_user + " " +database_pass);
      conn = DriverManager.getConnection(database_url, database_user, database_pass);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }
}
