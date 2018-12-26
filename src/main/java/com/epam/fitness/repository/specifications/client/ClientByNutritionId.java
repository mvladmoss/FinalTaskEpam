package com.epam.fitness.repository.specifications.client;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class ClientByNutritionId implements SqlSpecification {

    private Long nutritionId;

    public ClientByNutritionId(Long nutritionId) {
        this.nutritionId = nutritionId;
    }

    @Override
    public String getSql() {
        return "  left join program on client.program_id=program.id_program where nutrition_id = (?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(nutritionId);
    }



}
