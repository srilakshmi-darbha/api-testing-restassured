package com.thoughtworks.qabootcamp;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFactory {
    final static String PROPERTIES_FILE_PATH = System.getProperty("user.dir")+"/src/application_test.properties";

    static Properties properties;

    private static void readProperties() throws IOException {
        properties = new Properties();
        FileReader fileReader = new FileReader(PROPERTIES_FILE_PATH);
        properties.load(fileReader);
    }

    public static Properties getProperties() throws IOException {
        if(properties==null){
            readProperties();
        }
        return properties;
    }

    public static String getBaseUrl() throws IOException {
        return getProperties().getProperty("base_url");
    }
}