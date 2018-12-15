package com.epam.fitness.repository;

import com.epam.fitness.builder.ExerciseDtoBuilder;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ExerciseDtoRepository extends AbstractRepository<ExerciseDto> {

    public ExerciseDtoRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    public List<ExerciseDto> query(SqlSpecification specification) throws RepositoryException{
        String query = "select * from exercise " + specification.getSql();
        List<ExerciseDto> exercises = executeQuery(query,new ExerciseDtoBuilder(), specification.getParameters());
        return exercises;
    }

    @Override
    public Optional<ExerciseDto> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<ExerciseDto> exercise = query(specification);
        return exercise.size() == 1 ?
                Optional.of(exercise.get(0)) :
                Optional.empty();
    }


}
