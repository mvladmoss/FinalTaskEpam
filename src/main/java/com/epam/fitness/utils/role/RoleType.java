package com.epam.fitness.utils.role;

public enum RoleType {

    CLIENT("client"),
    COACH("coach");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
