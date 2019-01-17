package com.epam.fitness.connection;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;


public final class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());
    private BlockingQueue<ProxyConnection> connectionQueue;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private ConnectionConfigurationCreator connectionConfigurationCreator;
    private final static String PROPERTIES_FILE_NAME = "database.properties";
    private final static String URL = "url";
    private final static String USER = "user";
    private final static String PASSWORD = "password";
    private final static String DRIVER = "driver";
    private final static String POOLSIZE = "poolSize";

    private String user;
    private String password;
    private String driver;
    private int poolSize;
    private String url;

    private static final ConnectionPool INSTANCE = new ConnectionPool();



    private ConnectionPool() {
        if (instanceCreated.get()) {
            LOGGER.fatal("Tried to clone connection pool with reflection api");
            throw new RuntimeException("Tried to clone connection pool with reflection api");
        }
        instanceCreated.set(true);
    }


    public void initPool() {
        initPoolData();
        createConnections();
    }


    private void createConnections() {
        for (int index = 0; index < poolSize; index++) {
            try {
                Connection dbConnection = DriverManager.getConnection(url,user,password);
                ProxyConnection proxyConnection = new ProxyConnection(dbConnection);
                connectionQueue.put(proxyConnection);
            } catch (InterruptedException | SQLException e) {
                LOGGER.fatal(e.getMessage());
                throw new RuntimeException("Hasn't found connection with database");
            }
        }
    }


    private void initPoolData() {
        connectionQueue = new LinkedBlockingQueue<>();
        connectionConfigurationCreator = new ConnectionConfigurationCreator();
        try {
            Map<String,String> configurationDataMap = connectionConfigurationCreator.readDatabaseConfiguration(PROPERTIES_FILE_NAME);
            url = configurationDataMap.get(URL);
            user = configurationDataMap.get(USER);
            password = configurationDataMap.get(PASSWORD);
            driver = configurationDataMap.get(DRIVER);
            String poolSizeString = configurationDataMap.get(POOLSIZE);
            poolSize = Integer.parseInt(poolSizeString);
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new IllegalArgumentException("Driver is not found! " + e.getMessage(), e);
        }

    }


    public static ConnectionPool getInstance() {
        return INSTANCE;
    }


    public ProxyConnection takeConnection(){
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            connectionQueue.put(connection);

        } catch (InterruptedException | SQLException e){
            LOGGER.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage(),e);
        }

    }

    public void dispose() {
        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection proxyConnection = connectionQueue.take();
                if (!proxyConnection.getAutoCommit()) {
                    proxyConnection.commit();
                }
                proxyConnection.connection.close();
            } catch (InterruptedException | SQLException e) {
                LOGGER.error(e.getMessage());
                throw new IllegalArgumentException(e.getMessage(),e);
            }
        }
        unregisterDriver();
    }

    private void unregisterDriver() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == loader) {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                    throw new IllegalArgumentException(e.getMessage(),e);
                }
            }
        }
    }

}