package com.epam.fitness.repository.database.constants;

public enum NutritionTableConstants {

    ID("id_nutrition"),
    NAME("name"),
    MORNING_NUTRITION("morning_nutrition"),
    LUNCH_NUTRITION("lunch_nutrition"),
    DINNER_NUTRITION("dinner_nutrition");

    private String fieldName;
    private NutritionTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName(){
        return fieldName;
    }
}



