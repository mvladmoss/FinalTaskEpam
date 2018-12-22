package com.epam.fitness.repository.specifications.coach;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.Arrays;
import java.util.List;

public class CoachByLoginAndPassword implements SqlSpecification {

    private String login;
    private String password;

    public CoachByLoginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getSql() {
        return " where login = (?) and password = (?)";
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(login, password);
    }


}
