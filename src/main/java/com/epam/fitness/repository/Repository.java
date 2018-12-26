package com.epam.fitness.repository;

import com.epam.fitness.exception.ConnectionPoolException;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.List;
import java.util.Optional;

public interface Repository<T>{
    List<T> query(SqlSpecification specification) throws RepositoryException, ConnectionPoolException;
    Optional<T> queryForSingleResult(SqlSpecification specification) throws RepositoryException;
    Optional<T> findById(Long id) throws RepositoryException;
    List<T> findAll() throws RepositoryException;
    Long getNextTableId() throws RepositoryException;
}
