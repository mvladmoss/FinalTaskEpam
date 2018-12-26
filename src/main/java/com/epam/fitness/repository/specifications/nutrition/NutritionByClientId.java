package com.epam.fitness.repository.specifications.nutrition;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class NutritionByClientId implements SqlSpecification {

    private Long clientId;

    public NutritionByClientId(Long clientId){
        this.clientId = clientId;
    }

    @Override
    public String getSql() {
        return " left join program on nutrition.id_nutrition=program.nutrition_id left join client " +
                "on client.program_id = program.id_program where id_client = (?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(clientId);
    }
}
