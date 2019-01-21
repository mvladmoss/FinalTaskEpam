package com.epam.fitness.repository.database.constants;

public enum ProgramTableConstants {

    ID("id_program"),
    NUTRITION_ID("nutrition_id"),
    TRAINS_PER_WEEK("trains_per_week");

    private String fieldName;
    private ProgramTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName(){
        return fieldName;
    }
}




