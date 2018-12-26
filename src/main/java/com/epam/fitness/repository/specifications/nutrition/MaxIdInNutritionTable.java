package com.epam.fitness.repository.specifications.nutrition;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class MaxIdInNutritionTable implements SqlSpecification {
    @Override
    public String getSql() {
        return " order by(id_nutrition) desc limit 1";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.emptyList();
    }
}
