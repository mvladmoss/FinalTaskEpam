package com.epam.fitness.repository.specifications.exercise.dto;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class ExerciseDtoByProgramId implements SqlSpecification {

    private Long programId;

    public ExerciseDtoByProgramId(Long programId){
        this.programId = programId;
    }

    @Override
    public String getSql() {
        return "left join exercise_program on exercise.id_exercise = exercise_program.exercise_id where program_id=(?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(programId);
    }
}
