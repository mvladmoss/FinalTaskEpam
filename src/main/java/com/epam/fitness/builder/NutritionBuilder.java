package com.epam.fitness.builder;

import com.epam.fitness.model.Nutrition;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NutritionBuilder implements Builder<Nutrition> {
    @Override
    public Nutrition build(ResultSet resultSet){
        Nutrition nutrition = new Nutrition();
        try {
            Long idNutrition = resultSet.getLong("id_nutrition");
            nutrition.setId(idNutrition);
            String name = resultSet.getString("name");
            nutrition.setName(name);
            String description = resultSet.getString("description");
            nutrition.setDescription(description);
        }catch (SQLException exception){
            //Log
            throw  new RuntimeException();
        }
        return nutrition;
    }
}
