package grpc;

import java.io.*;
import java.util.Properties;

public class PropertiesHelper {
    String rootPath = "./src/main/resources/";
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

    public void saveProperties () throws IOException {
        if(authProps != null) {
            authProps.store(new FileOutputStream(authPropertiesPath), null);
        }
    }

    public String getAuthProperty(String key) {
        return authProps.getProperty(key);
    }

    public void setAuthProperty(String key, String value) {
        authProps.setProperty(key, value);
    }
}
