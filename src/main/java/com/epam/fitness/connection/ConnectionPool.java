package com.epam.fitness.connection;

import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.connection.ProxyConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;


public final class ConnectionPool {


    private BlockingQueue<ProxyConnection> connectionQueue;
    /*
     * check if instance created
     */
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    private String user;
    private String password;
    private String driver;

    private int poolSize;

    private String url;

    private static final ConnectionPool INSTANCE = new ConnectionPool();



    private ConnectionPool() {
        if (instanceCreated.get()) {
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
                throw new RuntimeException("Hasn't found connection with database");
            }
        }
    }


    private void initPoolData() {
        connectionQueue = new LinkedBlockingQueue<>();

        try {
            Class<? extends ConnectionPool> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("database.properties");

            Properties property = new Properties();
            property.load(inputStream);

            url = property.getProperty("db.url");
            user = property.getProperty("db.user");
            password = property.getProperty("db.password");
            driver = property.getProperty("db.driver");

            String poolSizeString = property.getProperty("db.poolSize");
            poolSize = Integer.parseInt(poolSizeString);

            Class.forName(driver);

        } catch (IOException e) {
            throw new IllegalArgumentException("File with properties not found! " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
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
            throw new IllegalArgumentException(e);
        }
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            if (!connectionQueue.contains(connection)) {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
                connectionQueue.put(connection);
            }

        } catch (InterruptedException | SQLException e) {
            try {
                Connection dbConnection = DriverManager.getConnection(url, user,password);
                ProxyConnection proxyConnection = new ProxyConnection(dbConnection);
                connectionQueue.put(proxyConnection);
                connection.connection.close();
            } catch (SQLException | InterruptedException e1) {
            }
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
            }
        }

        unregisterDriver();

    }


    private void unregisterDriver() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == loader) {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                }
            }
        }
    }

}