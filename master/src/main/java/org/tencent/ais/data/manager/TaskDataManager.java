package org.tencent.ais.data.manager;

import org.tencent.ais.data.util.DBUtils;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by iwardzhong on 2017/2/17.
 */
public class TaskDataManager {
  private DBUtils dbUtils = DBUtils.getInstance();
//  Map<Integer, TaskData> taskDataMap = new HashMap<>();

//  public TaskData getTaskDataById(int id) {
//    if (taskDataMap.containsKey(id)) {
//      return taskDataMap.get(id);
//    }
//    return null;
//  }
//
//  public void setTaskData(TaskData taskData) {
//    if (taskData != null) {
//      if (taskData.getTaskId() != 0) {
//        taskDataMap.put(taskData.getTaskId(), taskData);
//      } else {
//        taskDataMap.put(taskData.getAccessId(), taskData);
//      }
//    }
//  }

  private String dateFormat(Timestamp timestamp) {
    Date date = null;
    if (timestamp != null) {
      date = new Date(timestamp.getTime());
    }
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String result = format.format(date);
    return result;
  }


  public List<TaskData> getTaskByPlatformIdFromDB(int platformId, int status,
                                                  boolean useConditionOfPlatform) throws Exception{
    String sql;
    if (useConditionOfPlatform) {
      sql = "select * from task_access_test where platform_id = " +
              platformId + " and status = " + status;
    } else {
      sql = "select * from task_access_test where status = " + status;
    }

    TaskData taskData = null;
    List<TaskData> taskDataList = new ArrayList<>();
    ResultSet rs = dbUtils.execQuery(sql);
    while (rs.next()) {
      taskData = new TaskData();
      taskData.setDataType(1);
      taskData.setTaskId(rs.getInt("task_id"));
      taskData.setAlgoId(rs.getInt("algo_id"));
      taskData.setVersion(rs.getInt("version"));
      taskData.setPlatformId(rs.getInt("platform_id"));
      taskData.setInputFormat(rs.getString("input_format"));
      taskData.setInputPath(rs.getString("input_path"));
      taskData.setOutputPath(rs.getString("output_path"));
      taskData.setParameterPath(rs.getString("parameter_path"));
      taskData.setPackagePath(rs.getString("package_path"));
      taskData.setTaskType(rs.getInt("task_type"));
      taskData.setRate(rs.getInt("rate"));
      taskData.setUserId(rs.getInt("user_id"));
      taskData.setTaskInfo(rs.getString("task_info"));
      taskData.setSubmitTime(dateFormat(rs.getTimestamp("submit_time")));
      taskData.setStartTime(dateFormat(rs.getTimestamp("start_time")));
      taskData.setEndTime(dateFormat(rs.getTimestamp("end_time")));
      taskData.setModifyTime(dateFormat(rs.getTimestamp("modify_time")));

      // test
      taskData.setAccessStatus(0);
      taskData.setAccessId(1);
      taskData.setAccessProgress(100);
      taskData.setAlgoDescription("aaa");
      taskData.setAlgoName("aaa");

      taskDataList.add(taskData);
    }

    return taskDataList;
  }

  public List<TaskData> getAccessTaskByPlatformIdFromDB(
          int platformId, int status, boolean useConditionOfPlatform) throws Exception{
    String sql;
    if (useConditionOfPlatform) {
      sql = "select * from algo_access_tmp_iward where platform_id = " +
              platformId + " and access_status = " + status;
    } else {
      sql = "select * from algo_access_tmp_iward where access_status = " + status;
    }

    TaskData taskData = null;
    List<TaskData> taskDataList = new ArrayList<>();
    ResultSet rs = dbUtils.execQuery(sql);
    while (rs.next()) {
      taskData = new TaskData();
      taskData.setDataType(0);
      taskData.setAccessId(rs.getInt("access_id"));
      taskData.setAlgoId(rs.getInt("algo_id"));
      taskData.setAlgoName(rs.getString("algo_name"));
      taskData.setPackagePath(rs.getString("package"));
      taskData.setVersion(rs.getInt("version"));
      taskData.setPlatformId(rs.getInt("platform_id"));
      taskData.setAccessStatus(rs.getInt("access_status"));
      taskData.setAccessProgress(rs.getInt("access_progress"));
      taskData.setAlgoDescription(rs.getString("algo_description"));
      taskData.setSubmitTime(dateFormat(rs.getTimestamp("submit_time")));
      taskData.setStartTime(dateFormat(rs.getTimestamp("start_time")));
      taskData.setEndTime(dateFormat(rs.getTimestamp("end_time")));
      taskData.setModifyTime(dateFormat(rs.getTimestamp("modify_time")));

      // test
      taskData.setTaskId(1);
      taskData.setInputFormat("aaa");
      taskData.setInputPath("aaa");
      taskData.setOutputPath("aaa");
      taskData.setParameterPath("aaa");
      taskData.setTaskType(1);
      taskData.setRate(100);
      taskData.setUserId(12);
      taskData.setTaskInfo("aaa");

      taskDataList.add(taskData);
    }

    return taskDataList;
  }

  public boolean updateTaskStatusByTaskId(int taskId, int status, int rate,
                                          String errmsg, String startTime, String endTime) {
    String sql = "update task_access_test set status = " + status + ", rate = " + rate +
            ", err_msg = " + errmsg + ", start_time = '" + startTime + "', end_time = '" +
            endTime + "' where task_id = " + taskId;
    System.out.println(sql);
    boolean res = dbUtils.execUpdate(sql);
    return res;
  }

  public boolean updateAccessTaskStatusByTaskId(int accessId, int status, int rate,
                                                String startTime, String endTime) {
    String sql = "update algo_access_tmp_iward set access_status = " + status +
            ", access_progress = " + rate + ", start_time = '" + startTime +
            "', end_time = '" + endTime + "' where access_id = " + accessId;
    boolean res = dbUtils.execUpdate(sql);
    return res;
  }

}
