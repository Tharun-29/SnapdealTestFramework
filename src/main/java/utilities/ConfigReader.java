package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // Properties object to store configuration data
    public Properties properties;

    // Load properties from file
    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/java/resources/GlobalData.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load properties file: " + e.getMessage());
        }
    }

    // Retrieve property value by key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}