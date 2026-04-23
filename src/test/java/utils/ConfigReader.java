package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = new Properties();
    private static final String DEFAULT_ENV = "qa";

    static {
        loadProperties();
    }

    private ConfigReader() {
    }

    private static void loadProperties() {
        String env = System.getProperty("env", DEFAULT_ENV);
        String resourcePath = String.format("config/%s.properties", env);

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Environment config not found in classpath: " + resourcePath);
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load environment config: " + resourcePath, e);
        }
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        return systemValue != null && !systemValue.isBlank() ? systemValue : PROPERTIES.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
