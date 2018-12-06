package com.epam.fitness.repository.creator;


import com.epam.fitness.connection.ConnectionPool;
import com.epam.fitness.connection.ProxyConnection;
import com.epam.fitness.repository.ClientRepository;
import com.epam.fitness.repository.CoachRepository;
import com.epam.fitness.repository.OrderRepository;

import java.sql.Connection;


public class RepositoryCreator implements AutoCloseable {
    private ConnectionPool connectionPool;
    private Connection connection;

    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
    }

    public ClientRepository getClientRepository() {
        return new ClientRepository(connection);
    }


    public CoachRepository getCoachRepository() {
        return new CoachRepository(connection);
    }


    public OrderRepository getOrderRepository() {
        return new OrderRepository(connection);
    }


    @Override
    public void close() {
        connectionPool.releaseConnection((ProxyConnection) connection);
    }
}
