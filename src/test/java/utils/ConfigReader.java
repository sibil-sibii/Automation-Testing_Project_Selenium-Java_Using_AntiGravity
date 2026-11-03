package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// This class reads configuration properties from config.properties file
public class ConfigReader {

    private Properties properties;

    // Constructor loads the properties file
    public ConfigReader() {
        properties = new Properties();
        String configFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load config.properties from: " + configFilePath);
            e.printStackTrace();
        }
    }

    // This method returns the property value for the given key
    public String getProperties(String key) {
        return properties.getProperty(key);
    }

    // Alias helper method to get property value
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

