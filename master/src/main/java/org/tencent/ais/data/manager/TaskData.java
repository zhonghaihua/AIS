package org.tencent.ais.data.manager;

import java.util.Date;

/**
 * Created by iwardzhong on 2017/2/21.
 */
public class TaskData {

  public int dataType; // 0代表是任务接入，1代表是任务运行

  // 任务运行
  public int taskId;
  public int algoId;
  public int version;
  public int platformId;
  public String inputFormat;
  public String inputPath;
  public String outputPath;
  public String parameterPath;
  public String packagePath;
  public int taskType;
  public String submitTime;
  public String startTime;
  public String endTime;
  public String modifyTime;
  public int status;
  public int rate;
  public String errMsg;
  public int userId;
  public String taskInfo;

  // 任务接入
  public int accessId;
  // public int algoId;任务运行已有
  public String algoName;
  // public String packagePaht;任务运行已有
  // public String version;任务运行已有
  // public String platformId;任务运行已有
  public int accessStatus;
  public int accessProgress;
  public String algoDescription;
  //  public Date submitTime;任务运行已有
  //  public Date startTime;任务运行已有
  //  public Date endTime;任务运行已有
  //  public Date modifyTime; 任务运行已有

  public int getDataType() {
    return dataType;
  }

  public void setDataType(int dataType) {
    this.dataType = dataType;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public int getAlgoId() {
    return algoId;
  }

  public void setAlgoId(int algoId) {
    this.algoId = algoId;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public int getPlatformId() {
    return platformId;
  }

  public void setPlatformId(int platformId) {
    this.platformId = platformId;
  }

  public String getInputFormat() {
    return inputFormat;
  }

  public void setInputFormat(String inputFormat) {
    this.inputFormat = inputFormat;
  }

  public String getInputPath() {
    return inputPath;
  }

  public void setInputPath(String inputPath) {
    this.inputPath = inputPath;
  }

  public String getOutputPath() {
    return outputPath;
  }

  public void setOutputPath(String outputPath) {
    this.outputPath = outputPath;
  }

  public String getParameterPath() {
    return parameterPath;
  }

  public void setParameterPath(String parameterPath) {
    this.parameterPath = parameterPath;
  }

  public String getPackagePath() {
    return packagePath;
  }

  public void setPackagePath(String packagePath) {
    this.packagePath = packagePath;
  }

  public int getTaskType() {
    return taskType;
  }

  public void setTaskType(int taskType) {
    this.taskType = taskType;
  }

  public String getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(String submitTime) {
    this.submitTime = submitTime;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(String modifyTime) {
    this.modifyTime = modifyTime;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getTaskInfo() {
    return taskInfo;
  }

  public void setTaskInfo(String taskInfo) {
    this.taskInfo = taskInfo;
  }

  public int getAccessId() {
    return accessId;
  }

  public void setAccessId(int accessId) {
    this.accessId = accessId;
  }

  public String getAlgoName() {
    return algoName;
  }

  public void setAlgoName(String algoName) {
    this.algoName = algoName;
  }

  public int getAccessStatus() {
    return accessStatus;
  }

  public void setAccessStatus(int accessStatus) {
    this.accessStatus = accessStatus;
  }

  public int getAccessProgress() {
    return accessProgress;
  }

  public void setAccessProgress(int accessProgress) {
    this.accessProgress = accessProgress;
  }

  public String getAlgoDescription() {
    return algoDescription;
  }

  public void setAlgoDescription(String algoDescription) {
    this.algoDescription = algoDescription;
  }
}
