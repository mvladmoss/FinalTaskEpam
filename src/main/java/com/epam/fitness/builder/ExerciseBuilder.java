package com.epam.fitness.builder;

import com.epam.fitness.model.Exercise;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseBuilder implements Builder<Exercise> {
    @Override
    public Exercise build(ResultSet resultSet) {
        Exercise exercise = new Exercise();
        try {
            Long id = resultSet.getLong("exercise.id_exercise");
            exercise.setId(id);
            Long equipmentId = resultSet.getLong("equipment_id");
            exercise.setEquipmentId(equipmentId);
            String name = resultSet.getString("name");
            exercise.setName(name);
            String description = resultSet.getString("description");
            exercise.setDescription(description);
        }catch (SQLException exception){
            throw new RuntimeException("Exception in " + this.getClass().getName() + "Builder");
        }
        return exercise;
    }
}
