package com.epam.fitness.repository.specifications.client;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.ArrayList;
import java.util.List;

public class ClientInfoByLogin implements SqlSpecification {
    private static final String SQL = "select id,coach_id,name,surname,password,visits_number,sale,isCorporate" +
            " from client where login = ?";
    private List<Object> parameters = new ArrayList<>();
    private ArrayList<String> fields = new ArrayList<>();

    public ClientInfoByLogin(String login){
        parameters.add(login);
        fields.add("id");
        fields.add("coach_id");
        fields.add("name");
        fields.add("surname");
        fields.add("password");
        fields.add("visits_number");
        fields.add("sale");
        fields.add("isCorporate");
    }
    @Override
    public String getSql() {
        return SQL;
    }

    @Override
    public List<Object> getParameters() {
        return parameters;
    }

    @Override
    public boolean isFieldRequired(String filed) {
        return fields.contains(filed);
    }
}
