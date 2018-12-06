package com.epam.fitness.builder;

import com.epam.fitness.model.Coach;

import java.sql.ResultSet;

public class CoachBuilder implements Builder<Coach> {
    @Override
    public Coach build(ResultSet resultSet) {
        return new Coach();
    }
}
