package com.epam.fitness.builder;

import com.epam.fitness.model.Program;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramBuilder implements Builder<Program> {
    @Override
    public Program build(ResultSet resultSet) {
        Program program = new Program();
        try {
            Long id = resultSet.getLong("id_program");
            program.setId(id);
            Long nutritionId = resultSet.getLong("nutrition_id");
            program.setNutritionId(nutritionId);
            String description = resultSet.getString("description");
            program.setDescription(description);
            int trainsPerWeek = resultSet.getInt("trains_per_week");
            program.setTrainsPerWeek(trainsPerWeek);
        }catch (SQLException exception){
            throw new RuntimeException("Exception in " + this.getClass().getName() + "Builder");
        }
        return program;
    }
}
