package com.epam.fitness.repository;

import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class OrderRepository extends AbstractRepository<OrderInformation> {


    public OrderRepository(Connection connection) {
        super(connection);
    }

    @Override
    public void add(OrderInformation entity) throws RepositoryException {

    }

    @Override
    public void delete(OrderInformation entity) throws RepositoryException {

    }

    @Override
    public void update(OrderInformation entity) throws RepositoryException {

    }

    @Override
    public List<OrderInformation> executeQuery(SqlSpecification specification) throws RepositoryException, ConnectionPoolException {
        return null;
    }

    @Override
    public Optional<OrderInformation> executeQueryForSingleResult(SqlSpecification specification) throws RepositoryException {
        return Optional.empty();
    }
}
