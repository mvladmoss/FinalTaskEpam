package com.epam.fitness.builder;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseBuilder implements Builder<Exercise> {
    @Override
    public Exercise build(ResultSet resultSet) throws RepositoryException {
        Exercise exercise = new Exercise();
        try {
            Long id = resultSet.getLong("exercise.id_exercise");
            exercise.setId(id);
            Long equipmentId = resultSet.getLong("equipment_id");
            exercise.setEquipmentId(equipmentId);
            String name = resultSet.getString("namee");
            exercise.setName(name);
            String description = resultSet.getString("description");
            exercise.setDescription(description);
            String image = resultSet.getString("image");
            exercise.setImage(image);
            return exercise;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }

    }
}
