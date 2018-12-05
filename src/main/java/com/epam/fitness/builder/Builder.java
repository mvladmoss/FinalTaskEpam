package com.epam.fitness.builder;

import java.sql.ResultSet;

public interface Builder<T> {
    T build(ResultSet resultSet);
}
