package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties prop = new Properties();

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            // Read environment from system property (default: qa)
            String env = System.getProperty("env", "qa");
            String fileName = String.format("src/test/resources/config.%s.properties", env);

            FileInputStream fis = new FileInputStream(fileName);
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static String getEmail() {
        return getProperty("email");
    }
    
    public static String getPwd() {
        return getProperty("password");
    }
    
    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }
    
    public static String getScrrenshotPath() {
        return getProperty("screenshotPath");
    }
    
    public static String getReportPath() {
        return getProperty("reportPath");
    }
}