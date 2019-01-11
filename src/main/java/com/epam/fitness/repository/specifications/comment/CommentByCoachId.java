package com.epam.fitness.repository.specifications.comment;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class CommentByCoachId implements SqlSpecification {

    private Long coachID;

    public CommentByCoachId(Long coachID) {
        this.coachID = coachID;
    }

    @Override
    public String getSql() {
        return " where coach_id = (?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(coachID);
    }
}
