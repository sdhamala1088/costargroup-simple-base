package costar.testing.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {

    private static final Map<String, Properties> pathPropertiesMap = new HashMap<>();
    private static Properties activeProperties;

    public static void load(String path) {
        if (pathPropertiesMap.containsKey(path)) {
        	activeProperties = pathPropertiesMap.get(path);
            return;
        }

        try (FileInputStream fis = new FileInputStream(path)) {
            Properties props = new Properties();
            props.load(fis);
            pathPropertiesMap.put(path, props);
            activeProperties = props;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + path, e);
        }
    }

    public static String get(String key) {
        if (activeProperties == null) {
            throw new IllegalStateException("No config file loaded. Call ConfigReader.load(..) first.");
        }
        return activeProperties.getProperty(key);
    }
}
