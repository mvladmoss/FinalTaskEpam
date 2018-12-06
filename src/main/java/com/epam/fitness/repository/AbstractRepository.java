package com.epam.fitness.repository;

import com.epam.fitness.builder.Builder;
import com.epam.fitness.connection.ConnectionPool;
import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.connection.ProxyConnection;
import com.epam.fitness.model.Client;
import com.epam.fitness.repository.specifications.SqlSpecification;
import com.epam.fitness.service.QueryPreparer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T> implements Repository<T> {

    Connection connection;

    AbstractRepository(Connection connection){
        this.connection = connection;
    }

    public List<T> executeQuery(String sql, Builder<T> builder,String... parameters) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try (ProxyConnection dbConnection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);

            QueryPreparer.prepare(preparedStatement, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return objects;
    }

    public Optional<T> executeQueryForSingleResult(String sql,Builder<T> builder,String... parameters) throws RepositoryException{

        List<T> items = executeQuery(sql,builder,parameters);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }
}
