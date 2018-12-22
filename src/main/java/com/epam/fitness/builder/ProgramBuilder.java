package com.epam.fitness.builder;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.model.Program;
import com.epam.fitness.service.NutritionService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProgramBuilder implements Builder<Program> {
    @Override
    public Program build(ResultSet resultSet) {
        Program program = new Program();
        try {
            Long id = resultSet.getLong("id_program");
            program.setId(id);
            Long nutritionId = resultSet.getLong("nutrition_id");
            NutritionService nutritionService = new NutritionService();
            try {
                Optional<Nutrition> nutritionOptional = nutritionService.findById(nutritionId);
                nutritionOptional.ifPresent(nutrition -> program.setNutrition(nutrition));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
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
