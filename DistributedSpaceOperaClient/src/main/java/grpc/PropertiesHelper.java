package grpc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String authPropertiesPath = rootPath + "auth.properties";

    Properties authProps;

    public PropertiesHelper() {
        try {
            authProps = new Properties();
            authProps.load(new FileInputStream(authPropertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAuthProperty(String key) {
        return authProps.getProperty(key);
    }

    public void setAuthProperty(String key, String value) {
        authProps.setProperty(key, value);
    }
}
