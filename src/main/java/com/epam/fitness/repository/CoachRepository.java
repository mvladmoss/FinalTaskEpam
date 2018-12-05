package com.epam.fitness.repository;

import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.model.Coach;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.List;
import java.util.Optional;

public class CoachRepository implements Repository<Coach> {
    @Override
    public void add(Coach entity) throws RepositoryException {

    }

    @Override
    public void delete(Coach entity) throws RepositoryException {

    }

    @Override
    public void update(Coach entity) throws RepositoryException {

    }

    @Override
    public List<Coach> executeQuery(SqlSpecification specification) throws RepositoryException, ConnectionPoolException {
        return null;
    }

    @Override
    public Optional<Coach> executeQueryForSingleResult(SqlSpecification specification) throws RepositoryException {
        return Optional.empty();
    }
}
