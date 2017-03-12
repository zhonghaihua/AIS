package org.tencent.ais.task.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.task.event.*;

import java.rmi.UnexpectedException;

/**
 * Created by iwardzhong on 2017/2/22.
 */
public class TaskSchedulerEventProcessLoop extends EventLoop {

  private Logger log = LoggerFactory.getLogger("TaskSchedulerEventProcessLoop");
  private TaskScheduler taskScheduler;

  public TaskSchedulerEventProcessLoop(TaskScheduler taskScheduler) {
    super("TaskSchedulerEventProcessLoop");
    this.taskScheduler = taskScheduler;
  }

  @Override
  protected void onStop() {
    // TODO cleanUp all the job and shutdown the master service
  }

  @Override
  protected void onStart() {
    // do nothing
  }

  @Override
  protected void onReceive(Event event) throws UnexpectedException {
    doOnReceive(event);
  }

  @Override
  protected void onError() {
    log.error("TaskSchedulerEventProcessLoop failed, shutdown the master service.");

    // TODO shutdown all job, stop the master service
  }

  private void doOnReceive(Event event) {
    if (event instanceof RunTaskEvent) {
      taskScheduler.handleTaskToRun(event);
    } else if (event instanceof CompleteTaskEvent) {
      taskScheduler.handleRunningTaskToComplete(event);
    } else if (event instanceof UpdateTaskEvent) {
      taskScheduler.handleUpdateTaskInfo(event);
    } else if (event instanceof FailedTaskEvent) {
      taskScheduler.handleRunningTaskToFailed(event);
    }
  }

}
