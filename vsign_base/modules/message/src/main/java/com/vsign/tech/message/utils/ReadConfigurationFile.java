package com.vsign.tech.message.utils;

import java.io.InputStream;
import java.util.Properties;

public class ReadConfigurationFile {

    public static Properties getProperties(String resourceName) {

        InputStream stream = ReadConfigurationFile.class.getClassLoader()
                .getResourceAsStream(resourceName);
        Properties p = new Properties();
        try {
            p.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}