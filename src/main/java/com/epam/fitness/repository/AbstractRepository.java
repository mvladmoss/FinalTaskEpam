package com.epam.fitness.repository;

import com.epam.fitness.builder.Builder;
import com.epam.fitness.builder.BuilderFactory;
import com.epam.fitness.connection.ConnectionPool;
import com.epam.fitness.connection.ProxyConnection;
import com.epam.fitness.uitls.StatemenUtil;

import java.sql.*;
import java.util.*;

public abstract class AbstractRepository<T> implements Repository<T> {

    Connection connection;

    private static final String GET_ALL_QUERY = "SELECT * FROM ";
    private String WHERE_ID_CONDITION = " WHERE id_" + getTableName() + "=(?)";

    AbstractRepository(Connection connection){
        this.connection = connection;
    }

    protected abstract String getTableName();

    public List<T> executeQuery(String sql, Builder<T> builder,List<Object> parameters) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try (ProxyConnection dbConnection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);

            StatemenUtil.prepare(preparedStatement, parameters);
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

    protected Optional<T> executeQueryForSingleResult(String query, Builder<T> builder, List<Object> parameters) throws RepositoryException {

        List<T> items = executeQuery(query, builder, parameters);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    protected long executeUpdate(String query, List<Object> params) throws RepositoryException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            StatemenUtil.prepare(preparedStatement, params);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            long generatedKey = 0;
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getLong(1);
            }
            return generatedKey;
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<T> findById(Long id) throws RepositoryException {
        Builder builder = BuilderFactory.create(getTableName());
        String query = GET_ALL_QUERY + getTableName() + WHERE_ID_CONDITION;
        return executeQueryForSingleResult(query, builder, Arrays.asList(id));
    }

    @Override
    public List<T> findAll() throws RepositoryException {
        Builder builder = BuilderFactory.create(getTableName());
        String query = GET_ALL_QUERY + getTableName();
        return executeQuery(query, builder,Collections.emptyList());
    }

}
