package com.epam.fitness.repository.specifications.order;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LastOrderByClientId implements SqlSpecification {

    private Long id;

    public LastOrderByClientId(long id){
        this.id = id;
    }

    @Override
    public String getSql() {
        return " where client_id = (?) and end_date >= current_date() order by end_date desc limit 1";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(id);
    }
}