package org.tencent.ais.server;

import org.tencent.ais.data.manager.TaskDataManager;
import org.tencent.ais.data.util.CommonConf;
import org.tencent.ais.task.manager.TaskManager;
import org.tencent.ais.task.manager.TaskSetManager;
import org.tencent.ais.task.scheduler.TaskScheduler;

/**
 * Created by iwardzhong on 2017/2/27.
 */
public class Master {
  private TaskScheduler taskScheduler;
  private TaskManager taskManager;
  private TaskSetManager taskSetManager;
  private TaskDataManager taskDataManager;
  private AISServer server;

  public Master() {
    initialize();
  }

  public void initialize() {
    taskDataManager = new TaskDataManager();
    taskSetManager = TaskSetManager.getTaskSetManagerInstance();
    taskScheduler = TaskScheduler.getInstance(taskSetManager, taskDataManager);
    taskManager = new TaskManager(taskDataManager, taskSetManager, taskScheduler);
    taskManager.initialize();
    taskScheduler.initialize();
    server = AISServer.createServer();
    server.start();
  }

  public static void main(String argv[]) throws Exception {
    CommonConf.running = true;
    Master master = new Master();

    if (master != null) {
      master.join();
    }
  }

  public void join() {
    try {
      server.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public boolean stop() {
    boolean res = true;
    // TODO 将所有任务都置为失败状态，停止所有线程(可以不停止，外围终端会直接kill掉这个进程),会有一个rpc服务接口调用这个方法
    return res;
  }


}
