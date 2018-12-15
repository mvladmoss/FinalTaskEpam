package com.epam.fitness.repository.specifications.client;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class ClientsByCoachIdSpecification implements SqlSpecification {

    private Long coachId;

    public ClientsByCoachIdSpecification(long coachId){
        this.coachId = coachId;
    }
    @Override
    public String getSql() {
        return " where coach_id=(?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(coachId);
    }

}
