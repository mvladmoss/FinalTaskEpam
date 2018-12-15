package com.epam.fitness.repository.specifications.program;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class ProgramByIdSpecification implements SqlSpecification {

    private Long programId;

    public ProgramByIdSpecification(Long programId){
        this.programId = programId;
    }

    @Override
    public String getSql() {
        return "where id_program=(?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(programId);
    }
}
