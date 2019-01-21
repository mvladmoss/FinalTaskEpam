package com.epam.fitness.repository.database.constants;

public enum ExerciseTableConstants {

    ID("id_exercise"),
    NAME("name"),
    DESCRIPTION("description"),
    IMAGE("image");

    private String fieldName;
    private ExerciseTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName(){
        return fieldName;
    }
}


