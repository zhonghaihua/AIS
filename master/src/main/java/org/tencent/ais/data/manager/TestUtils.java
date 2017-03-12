package org.tencent.ais.data.manager;

import org.junit.Test;

import java.util.List;

/**
 * Created by iwardzhong on 2017/3/2.
 */
public class TestUtils {

  @Test
  public void testConnectDB() throws Exception {
    TaskDataManager taskDataManager = new TaskDataManager();
    List<TaskData> taskDataList = taskDataManager.getAccessTaskByPlatformIdFromDB(0, 0, false);
    System.out.println(taskDataList.size());
  }

  public static void main(String argv[]) throws Exception {
    TaskDataManager taskDataManager = new TaskDataManager();
    List<TaskData> taskDataList = taskDataManager.getAccessTaskByPlatformIdFromDB(0, 0, false);
    System.out.println(taskDataList.size());
  }
}
