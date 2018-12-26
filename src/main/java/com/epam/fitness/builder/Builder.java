package com.epam.fitness.builder;

import com.epam.fitness.model.Identifiable;
import com.epam.fitness.exception.RepositoryException;

import java.sql.ResultSet;

public interface Builder<T extends Identifiable> {
    T build(ResultSet resultSet) throws RepositoryException;
}
