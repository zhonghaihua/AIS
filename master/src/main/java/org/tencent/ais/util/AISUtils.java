package org.tencent.ais.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tencent.ais.executor.manager.ExecutorManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by iwardzhong on 2017/3/8.
 */
public class AISUtils {

  private static Logger log = LoggerFactory.getLogger(AISUtils.class);

  public synchronized static String execuShellCmd(String cmd) throws IOException {
    Runtime rt = Runtime.getRuntime();
    InputStream stdin = null;
    String line = "";
    try {
      Process proc = rt.exec(new String[]{"/bin/sh", "-c", cmd}, null, null);
      stdin = proc.getInputStream();
      List<String> results = IOUtils.readLines(stdin);
      for (String result : results) {
        line = line + result;
      }
      return line.trim();
    } catch (Exception e) {
      log.error("execu shell command: " + cmd + " failed");
      throw new IOException("execu shell command: " + cmd + "failed");
    } finally {
      IOUtils.closeQuietly(stdin);
    }
  }

  public synchronized static String asynExecuShellCmd(String cmd) throws IOException {
    Runtime rt = Runtime.getRuntime();
    InputStream stdin = null;
    String line = "";
    try {
      Process proc = rt.exec(new String[]{"/bin/sh", "-c", cmd}, null, null);
//      stdin = proc.getInputStream();
//      List<String> results = IOUtils.readLines(stdin);
//      for (String result : results) {
//        line = line + result;
//      }
      return String.valueOf(proc.exitValue());
    } catch (Exception e) {
      log.error("execu shell command: " + cmd + " failed");
      throw new IOException("execu shell command: " + cmd + "failed");
    } finally {
      IOUtils.closeQuietly(stdin);
    }
  }

  public synchronized static String execuShellCmd(String executorId, String cmd) throws IOException {
    Runtime rt = Runtime.getRuntime();
    InputStream stdin = null;
    String line = "";
    try {
      Process proc = rt.exec(new String[]{"/bin/sh", "-c", cmd}, null, null);
      stdin = proc.getInputStream();
      List<String> results = IOUtils.readLines(stdin);
      for (String result : results) {
        line = line + result;
      }
      ExecutorManager.getInstance().setExecutorIdToProcess(executorId, proc);
      return line.trim();
    } catch (Exception e) {
      log.error("execu shell command: " + cmd + " failed");
      throw new IOException("execu shell command: " + cmd + "failed");
    } finally {
      IOUtils.closeQuietly(stdin);
    }
  }

  public synchronized static String asynExecuShellCmd(String executorId, String cmd) throws IOException {
    Runtime rt = Runtime.getRuntime();
    InputStream stdin = null;
    String line = "";
    try {
      Process proc = rt.exec(new String[]{"/bin/sh", "-c", cmd}, null, null);
//      stdin = proc.getInputStream();
//      List<String> results = IOUtils.readLines(stdin);
//      for (String result : results) {
//        line = line + result;
//      }
      ExecutorManager.getInstance().setExecutorIdToProcess(executorId, proc);
      return String.valueOf(proc.exitValue());
    } catch (Exception e) {
      log.error("execu shell command: " + cmd + " failed");
      throw new IOException("execu shell command: " + cmd + "failed");
    } finally {
      IOUtils.closeQuietly(stdin);
    }
  }
}
