package com.epam.fitness.builder;

public class BuilderFactory {

    private static final String CLIENT = "client";
    private static final String COACH = "coach";


    public static Builder create(String builderName) {

        switch (builderName) {
            case CLIENT:
                return new ClientBuilder();
            case COACH:
                return new CoachBuilder();
            default:
                throw new IllegalArgumentException("Unknown Builder name!");
        }
    }

}
