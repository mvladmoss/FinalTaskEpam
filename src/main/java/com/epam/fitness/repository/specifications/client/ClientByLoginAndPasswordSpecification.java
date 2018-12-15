package com.epam.fitness.repository.specifications.client;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientByLoginAndPasswordSpecification implements SqlSpecification {
    private String login;
    private String password;

    public ClientByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getSql() {
        return " where login= (?) and password = (?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(login, password);
    }


}
