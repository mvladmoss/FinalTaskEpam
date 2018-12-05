package com.epam.fitness.repository.specifications.coach;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.ArrayList;
import java.util.List;

public class FindAllCoaches implements SqlSpecification {
    private static final String SQL = "select id,name,surname,login,password" +
            " from coach";
    private List<Object> parameters = new ArrayList<>();
    private ArrayList<String> fields = new ArrayList<>();

    public FindAllCoaches(long id){
        parameters.add(id);
        fields.add("id");
        fields.add("name");
        fields.add("surname");
        fields.add("login");
        fields.add("password");
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