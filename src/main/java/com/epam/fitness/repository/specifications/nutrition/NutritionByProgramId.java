package com.epam.fitness.repository.specifications.nutrition;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class NutritionByProgramId implements SqlSpecification {

    private Long programId;

    public NutritionByProgramId(Long programId){
        this.programId = programId;
    }

    @Override
    public String getSql() {
        return " left join program on nutrition.id_nutrition = program.nutrition_id where program.id_program=(?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(programId);
    }
}
