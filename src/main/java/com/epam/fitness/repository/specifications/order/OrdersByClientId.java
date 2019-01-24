package com.epam.fitness.repository.specifications.order;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Collections;
import java.util.List;

public class OrdersByClientId implements SqlSpecification {

    private Long id;

    public OrdersByClientId(long id){
        this.id = id;
    }

    @Override
    public String getSql() {
        return " where client_id = (?)";
    }

    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(id);
    }
}
