package com.epam.fitness.repository;

import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.List;
import java.util.Optional;

public interface Repository<T>{
    void add(T entity) throws RepositoryException;
    void delete(T entity) throws RepositoryException;
    void update(T entity) throws RepositoryException;
    List<T> executeQuery(SqlSpecification specification) throws RepositoryException, ConnectionPoolException;
    Optional<T> executeQueryForSingleResult(SqlSpecification specification) throws RepositoryException;
}
