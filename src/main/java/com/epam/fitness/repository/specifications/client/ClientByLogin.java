package com.epam.fitness.repository.specifications.client;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class ClientByLogin implements SqlSpecification {

    private String login;

    public ClientByLogin(String login) {
        this.login = login;
    }

    @Override
    public String getSql() {
        return  " where login=(?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(login);
    }
}
