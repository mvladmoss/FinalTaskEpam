package com.epam.fitness.builder;

import com.epam.fitness.model.Nutrition;
import com.epam.fitness.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NutritionBuilder implements Builder<Nutrition> {
    @Override
    public Nutrition build(ResultSet resultSet) throws RepositoryException {
        Nutrition nutrition = new Nutrition();
        try {
            Long idNutrition = resultSet.getLong("id_nutrition");
            nutrition.setId(idNutrition);
            String name = resultSet.getString("name");
            nutrition.setName(name);
            String description = resultSet.getString("description");
            nutrition.setDescription(description);
            return nutrition;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
