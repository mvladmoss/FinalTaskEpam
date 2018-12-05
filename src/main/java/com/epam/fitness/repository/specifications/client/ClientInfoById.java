package com.epam.fitness.repository.specifications.client;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.ArrayList;
import java.util.List;

public class ClientInfoById implements SqlSpecification {
    private static final String SQL = "select login,coach_id,name,surname,password,visits_number,sale,isCorporate" +
            " from client where id = ?";
    private List<Object> parameters = new ArrayList<>();
    private ArrayList<String> fields = new ArrayList<>();

    public ClientInfoById(long id){
        parameters.add(id);
        fields.add("login");
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
