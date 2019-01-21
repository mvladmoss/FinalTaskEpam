package com.epam.fitness.repository.database.constants;

public enum CoachTableConstants {

    ID("id_coach"),
    NAME("name"),
    SURNAME("surname"),
    LOGIN("login"),
    PASSWORD("password");

    private String fieldName;
    private CoachTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName(){
        return fieldName;
    }
}
