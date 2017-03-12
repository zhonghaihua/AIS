package org.tencent.ais.task.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.task.TaskInfo;
import org.tencent.ais.task.event.Event;
import org.tencent.ais.task.manager.TaskSetManager;

import java.rmi.UnexpectedException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by iwardzhong on 2017/2/22.
 */
public abstract class EventLoop {
  private LinkedBlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
  private AtomicBoolean stopped = new AtomicBoolean(false);
  private String threadName;
  private Logger log = LoggerFactory.getLogger("EventLogger");

  public EventLoop(String name) {
    this.threadName = name;
  }

  private Thread eventThread = new Thread(new Runnable() {
    @Override
    public void run() {
      while (!stopped.get()) {
        try {
          Event event = eventQueue.take();
          try {
            onReceive(event);
          } catch (UnexpectedException e) {
            onError();
            log.error("UnexpectedException error" + threadName, e);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  });

  public void start() {
    if (stopped.get()) {
      throw new IllegalThreadStateException(threadName + " has already been stopped");
    }
    onStart();
    eventThread.setDaemon(true);
    eventThread.start();
  }

  public void stop() {
    if (stopped.compareAndSet(false, true)) {
      eventThread.interrupt();
      boolean onStopCalled = false;
      try {
        eventThread.join();
        onStopCalled = true;
        onStop();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        if (!onStopCalled) {
          onStop();
        }
      }
    }
  }

  public void post(Event event) throws InterruptedException {
    eventQueue.put(event);
  }

  public boolean isActive() {
    return eventThread.isAlive();
  }

  protected abstract void onStop();

  protected abstract void onStart();

  protected abstract void onReceive(Event event) throws UnexpectedException;

  protected abstract void onError();


}
