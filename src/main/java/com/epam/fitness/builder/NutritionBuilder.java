package com.epam.fitness.builder;

import com.epam.fitness.model.Nutrition;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.database.constants.NutritionTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NutritionBuilder implements Builder<Nutrition> {
    @Override
    public Nutrition build(ResultSet resultSet) throws RepositoryException {
        Nutrition nutrition = new Nutrition();
        try {
            Long idNutrition = resultSet.getLong(NutritionTableConstants.ID.getFieldName());
            nutrition.setId(idNutrition);
            String name = resultSet.getString(NutritionTableConstants.NAME.getFieldName());
            nutrition.setName(name);
            String morningNutrition = resultSet.getString(NutritionTableConstants.MORNING_NUTRITION.getFieldName());
            nutrition.setMorningNutrition(morningNutrition);
            String lunchNutrition = resultSet.getString(NutritionTableConstants.LUNCH_NUTRITION.getFieldName());
            nutrition.setLunchNutrition(lunchNutrition);
            String dinnerNutrition = resultSet.getString(NutritionTableConstants.DINNER_NUTRITION.getFieldName());
            nutrition.setDinnerNutrition(dinnerNutrition);
            return nutrition;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
