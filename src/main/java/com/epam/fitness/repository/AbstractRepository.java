package com.epam.fitness.repository;

import com.epam.fitness.builder.Builder;
import com.epam.fitness.builder.BuilderFactory;
import com.epam.fitness.connection.ConnectionPool;
import com.epam.fitness.connection.ProxyConnection;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Identifiable;
import com.epam.fitness.utils.StatementUtil;

import java.sql.*;
import java.util.*;

public abstract class AbstractRepository<T extends Identifiable> implements Repository<T> {

    Connection connection;

    private static final String GET_ALL_QUERY = "SELECT * FROM ";
    private final String WHERE_ID_CONDITION = " WHERE id_" + getTableName() + "=(?)";
    private final String MAX_TABLE_ID_CONDITION = " order by(id_" + getTableName() + ") desc limit 1";

    AbstractRepository(Connection connection){
        this.connection = connection;
    }

    protected abstract String getTableName();

    public List<T> executeQuery(String sql, Builder<T> builder,List<Object> parameters) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try (ProxyConnection dbConnection = (ProxyConnection) ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);

            StatementUtil.prepare(preparedStatement, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }

        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(),e);
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

            StatementUtil.prepare(preparedStatement, params);
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

    public Long getNextTableId() throws RepositoryException {
        Builder builder = BuilderFactory.create(getTableName());
        String query = GET_ALL_QUERY + getTableName() + MAX_TABLE_ID_CONDITION;
        Optional<T> itemOptional = executeQueryForSingleResult(query, builder,Collections.emptyList());
        Long nextId = itemOptional.map( item -> item.getId() + 1).orElse(0l);
        return nextId;
    }

}
