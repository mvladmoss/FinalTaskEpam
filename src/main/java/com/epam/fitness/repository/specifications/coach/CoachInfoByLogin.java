package com.epam.fitness.repository.specifications.coach;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.List;

public class CoachInfoByLogin implements SqlSpecification {
    @Override
    public String getSql() {
        return null;
    }

    @Override
    public List<Object> getParameters() {
        return null;
    }

    @Override
    public boolean isFieldRequired(String filed) {
        return false;
    }
}
