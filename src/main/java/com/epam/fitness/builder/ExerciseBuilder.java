package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.repository.database.constants.ExerciseTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Designed to build an object of type {@link com.epam.fitness.model.Exercise} with specified characteristics.
 */
public class ExerciseBuilder implements Builder<Exercise> {

    /**
     * Builds an object of type Exercise with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object type Exercise.
     * @return Returns built object type Exercise.
     * @throws RepositoryException Throws when SQL Exception is caught.
     */

    @Override
    public Exercise build(ResultSet resultSet) throws RepositoryException {
        try {
            Long id = resultSet.getLong(ExerciseTableConstants.ID.getFieldName());
            String name = resultSet.getString(ExerciseTableConstants.NAME.getFieldName());
            String description = resultSet.getString(ExerciseTableConstants.DESCRIPTION.getFieldName());
            String image = resultSet.getString(ExerciseTableConstants.IMAGE.getFieldName());
            return new Exercise(id,name,description,image);
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }

    }
}
