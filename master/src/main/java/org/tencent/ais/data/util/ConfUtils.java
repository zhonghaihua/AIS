package org.tencent.ais.data.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by iwardzhong on 2017/2/20.
 */
public class ConfUtils {

  private static String confFileName = "ais-conf";
  private static Map<String, String> aisconf;

  private static String getEnv(String key) {
    return System.getenv(key);
  }

  private static String getConfDir() {
    String dir = getEnv("AIS_CONF_DIR");
    System.out.println("dir: " + dir);
    return dir;
  }

  private static Properties loadProperties() throws IOException {
    Properties props = new Properties();
    File propsFile;
    propsFile = new File(getConfDir(), confFileName);

    if (propsFile.isFile()) {
      FileInputStream fd = null;
      try {
        fd = new FileInputStream(propsFile);
        props.load(new InputStreamReader(fd, StandardCharsets.UTF_8));
        for (Map.Entry<Object, Object> e : props.entrySet()) {
          e.setValue(e.getValue().toString().trim());
        }
      } finally {
        if (fd != null) {
          try {
            fd.close();
          } catch (IOException e) {
            // Ignore.
          }
        }
      }
    }
    return props;
  }

  static Map<String, String> getConf() {
    if (aisconf == null) {
      aisconf = new HashMap<String, String>();
      Properties p = null;
      try {
        p = loadProperties();
      } catch (IOException e) {
        e.printStackTrace();
      }
      for (String key : p.stringPropertyNames()) {
        if (!aisconf.containsKey(key)) {
          aisconf.put(key, p.getProperty(key));
        }
      }
    }
    return aisconf;
  }

}
