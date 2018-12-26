package com.epam.fitness.repository;

import com.epam.fitness.builder.ExerciseBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ExerciseRepository extends AbstractRepository<Exercise> {

    private static final String TABLE_NAME = "exercise";

    public ExerciseRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Exercise> query(SqlSpecification specification) throws RepositoryException {
        String query = "select * from exercise " + specification.getSql();
        List<Exercise> exercises = executeQuery(query,new ExerciseBuilder(), specification.getParameters());
        return exercises;
    }

    @Override
    public Optional<Exercise> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<Exercise> exercise = query(specification);
        return exercise.size() == 1 ?
                Optional.of(exercise.get(0)) :
                Optional.empty();
    }
}
