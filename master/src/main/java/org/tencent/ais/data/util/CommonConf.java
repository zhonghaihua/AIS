package org.tencent.ais.data.util;

/**
 * Created by iwardzhong on 2017/2/20.
 */
public class CommonConf {
  public static String DATABASE_IP = ConfUtils.getConf().get("database.ip");
  public static String DATABASE_USER = ConfUtils.getConf().get("database.user");
  public static String DATABASE_PASS = ConfUtils.getConf().get("database.pass");
  public static String DATABASE_PORT = ConfUtils.getConf().get("database.port");
  public static String TASK_INITIAL = ConfUtils.getConf().get("task_initial");
  public static String TASK_RUNNING = ConfUtils.getConf().get("task_running");
  public static String TASK_SUCCESS = ConfUtils.getConf().get("task_success");
  public static String TASK_FAILED = ConfUtils.getConf().get("task_failed");
  public static String PLATFORM_TF = ConfUtils.getConf().get("platform_tf");
  public static String PLATFORM_XGBOOST = ConfUtils.getConf().get("platform_xgboost");
  public static String PLATFORM_MPI = ConfUtils.getConf().get("platform_mpi");
  public static String PALTFORM_SPARK = ConfUtils.getConf().get("platform_spark");
  public static int SERVER_PORT = Integer.parseInt(ConfUtils.getConf().get("server.port"));
  public static String SERVER_IP = ConfUtils.getConf().get("server.ip");
  public static int SERVER_TIMEOUT = Integer.parseInt(ConfUtils.getConf().get("server.timeout"));
  public static long EXECUTOR_TIMEOUT = Long.parseLong(ConfUtils.getConf().get("executor.timeout"));
  public static String EXECUTOR_MEMORY = ConfUtils.getConf().get("executor.memory");
  public static volatile boolean running;

}
