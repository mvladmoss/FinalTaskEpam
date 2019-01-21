package com.epam.fitness.repository.database.constants;

public enum OrderInformationTableConstants {

    ID("id_order_information"),
    COST("cost"),
    PAYMENT_DATA("payment_data"),
    MEMBERSHIP_END_DATE("membership_end_date"),
    CLIENT_ID("client_id");

    private String fieldName;
    private OrderInformationTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName(){
        return fieldName;
    }
}




