package com.epam.fitness.repository.database.constants;

public enum ExerciseDtoTableConstants {

    ID("id_exercise_program"),
    PROGRAM_ID("program_id"),
    EXERCISE_ID("exercise_id"),
    REPEAT_NUMBER("repeat_number"),
    SET_NUMBER("set_number"),
    NUMBER_TRAIN_DAY("number_train_day");

    private String fieldName;
    private ExerciseDtoTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName(){
        return fieldName;
    }
}

