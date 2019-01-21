package com.epam.fitness.repository.database.constants;

public enum ClientTableConstants {

    ID("id_client"),
    COACH_ID("coach_id"),
    NAME("name"),
    SURNAME("surname"),
    LOGIN("login"),
    PASSWORD("password"),
    MEMBERSHIP_PURCHASED_NUMBER("membership_purchased_number"),
    PERSONAL_DISCOUNT("personal_discount"),
    CORPORATE_DISCOUNT("corporate_discount"),
    PROGRAM_ID("PROGRAM_ID");

    private String fieldName;
    private ClientTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName(){
        return fieldName;
    }
}
