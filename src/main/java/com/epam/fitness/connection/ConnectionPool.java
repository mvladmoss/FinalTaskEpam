package com.epam.fitness.connection;

import com.epam.fitness.connection.ProxyConnection;
import org.apache.log4j.Logger;
import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Custom realization of ConnectionPool
 * for Pufar application
 */
public class ConnectionPool {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);


    /** The Constant PROPERTY_PATH. */
    private static final String PROPERTY_PATH = "database.properties";


    /** The Constant INITIAL_CAPACITY. */
    private static final int INITIAL_CAPACITY = 15;

    /** The free connections. */
    private ArrayBlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);

    /** The release connections. */
    private ArrayBlockingQueue<Connection> releaseConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);

    /** The lock. */
    private static ReentrantLock lock = new ReentrantLock();

    /** The connection pool. */
    private static ConnectionPool connectionPool;

    /**
     * Gets the single instance of ConnectionPool.
     *
     * @return single instance of ConnectionPool
     */
    public static ConnectionPool getInstance(){

        if(connectionPool == null){
            try {
                lock.lock();
                if(connectionPool == null){
                    connectionPool = new ConnectionPool();
                }
            }
            catch (SQLException e) {
                LOGGER.error("Can not get Instance", e);
                throw new RuntimeException("Can not get Instance", e);
            } finally {
                lock.unlock();
            }
        }

        return connectionPool;
    }

    /**
     * Instantiates a new connection pool.
     *
     * @throws SQLException the SQL exception
     */
    private ConnectionPool() throws SQLException {

        try {
            lock.lock();

            if(connectionPool != null){
                throw new UnsupportedOperationException();
            }
            else {
                DriverManager.registerDriver(new Driver());
                init();
            }
        }
        finally {
            lock.unlock();
        }

    }

    /**
     * Inits the.
     */
    private void init() {

        Properties properties = new Properties();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(PROPERTY_PATH);
            properties.load(inputStream);
        }catch (IOException e) {
            LOGGER.error("Error while reading properties", e);
        }
        String connectionURL = properties.getProperty("url");
        String initialCapacityString = properties.getProperty("poolSize");
        Integer initialCapacity = Integer.valueOf(initialCapacityString);

        for (int i = 0; i < initialCapacity; i++) {
            try {
                Connection connection = new ProxyConnection(DriverManager.getConnection(connectionURL, properties));
                freeConnections.add(connection);
            }
            catch (SQLException e) {
                LOGGER.error("Pool can not initialize", e);
                throw new RuntimeException("Pool can not initialize", e);
            }
        }

    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        try {
            Connection connection = freeConnections.take();
            releaseConnections.offer(connection);

            return connection;
        }
        catch (InterruptedException  e) {
            throw new RuntimeException("Can not get database", e);
        }

    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {

        releaseConnections.remove(connection);
        freeConnections.offer(connection);

    }

    /**
     * Destroy.
     */
    public void destroy(){

        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                ProxyConnection connection = (ProxyConnection) freeConnections.take();
                connection.realClose();
            }
            catch (InterruptedException e) {
                LOGGER.error("Connection close exception", e);
            }
        }

        try {
            Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                java.sql.Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);

            }
        }
        catch (SQLException e) {
            LOGGER.error("Drivers were not deregistrated", e);
        }

    }

}
