package com.epam.fitness.repository.specifications.exercise.dto;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class ExerciseDtoById implements SqlSpecification {

    private Long id;

    public ExerciseDtoById(Long id){
        this.id = id;
    }

    @Override
    public String getSql() {
        return "right join exercise_program on exercise.id_exercise = exercise_program.exercise_id where exercise_program.id=(?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(id);
    }
}
