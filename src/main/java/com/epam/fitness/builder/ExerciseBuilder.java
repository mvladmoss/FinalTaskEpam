package com.epam.fitness.builder;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.database.constants.ExerciseTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseBuilder implements Builder<Exercise> {
    @Override
    public Exercise build(ResultSet resultSet) throws RepositoryException {
        Exercise exercise = new Exercise();
        try {
            Long id = resultSet.getLong(ExerciseTableConstants.ID.getFieldName());
            exercise.setId(id);
            String name = resultSet.getString(ExerciseTableConstants.NAME.getFieldName());
            exercise.setName(name);
            String description = resultSet.getString(ExerciseTableConstants.DESCRIPTION.getFieldName());
            exercise.setDescription(description);
            String image = resultSet.getString(ExerciseTableConstants.IMAGE.getFieldName());
            exercise.setImage(image);
            return exercise;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }

    }
}
