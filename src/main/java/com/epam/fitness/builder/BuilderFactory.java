package com.epam.fitness.builder;

/**
 * Designed to build an object of type {@link Builder}.
 */
public class BuilderFactory {

    private static final String CLIENT = "client";
    private static final String COACH = "coach";
    private static final String EXERCISE = "exercise";
    private static final String PROGRAM = "program";
    private static final String NUTRITION = "nutrition";


    /**
     * Designed to build an object of type {@link Builder} depends on builder name.
     *
     * @param builderName a {@link String} object that contains builder name
     * @return an object of type {@link Builder}.
     */
    public static Builder create(String builderName) {

        switch (builderName) {
            case CLIENT: {
                return new ClientBuilder();
            }
            case COACH: {
                return new CoachBuilder();
            }
            case PROGRAM: {
                return new ProgramBuilder();
            }
            case EXERCISE:{
                return new ExerciseBuilder();
            }
            case NUTRITION:{
                return new NutritionBuilder();
            }

            default:
                throw new IllegalArgumentException("Unknown Builder name!");
        }
    }

}
