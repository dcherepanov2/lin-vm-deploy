package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public abstract class AbstractPropertiesUtil {

    private final Properties configuration;
    public AbstractPropertiesUtil() {
        this.configuration = new Properties();
    }

    public void  loadProperties(String path){
        try {
            configuration.load(Files.newInputStream(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }
    public String getProperty(String key){
        return configuration.getProperty(key);
    }

}
