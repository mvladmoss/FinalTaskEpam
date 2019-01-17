package com.epam.fitness.connection;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionConfigurationCreator {

    private static final Logger LOGGER = Logger.getLogger(ConnectionConfigurationCreator.class.getName());
    private final static String URL = "url";
    private final static String USER = "user";
    private final static String PASSWORD = "password";
    private final static String DRIVER = "driver";
    private final static String POOLSIZE = "poolSize";

    private final static String DATABASE_URL = "db.url";
    private final static String DATABASE_USER = "db.user";
    private final static String DATABASE_PASSWORD = "db.password";
    private final static String DATABASE_DRIVER = "db.driver";
    private final static String DATABASE_POOLSIZE = "db.poolSize";

    public Map<String,String> readDatabaseConfiguration(String propertiesFileName){
        Map<String,String> configurationDataMap = new HashMap<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(propertiesFileName);
            Properties property = new Properties();
            property.load(inputStream);
            String url = property.getProperty(DATABASE_URL);
            configurationDataMap.put(URL,url);
            String user = property.getProperty(DATABASE_USER);
            configurationDataMap.put(USER,user);
            String password = property.getProperty(DATABASE_PASSWORD);
            configurationDataMap.put(PASSWORD,password);
            String driver = property.getProperty(DATABASE_DRIVER);
            configurationDataMap.put(DRIVER,driver);

            String poolSizeString = property.getProperty(DATABASE_POOLSIZE);
            configurationDataMap.put(POOLSIZE,poolSizeString);
        }catch (IOException e){
            LOGGER.error(e.getMessage());
            throw new IllegalArgumentException("File with properties not found! " + e.getMessage(), e);
        }
        return configurationDataMap;
    }
}
