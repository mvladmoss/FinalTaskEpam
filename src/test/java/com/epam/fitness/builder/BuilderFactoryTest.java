package com.epam.fitness.builder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class BuilderFactoryTest {
    private static final String CLIENT = "client";
    private static final String COACH = "coach";
    private static final String EXERCISE = "exercise";
    private static final String PROGRAM = "program";
    private static final String NUTRITION = "nutrition";

    private static final Class CLIENT_BUILDER = ClientBuilder.class;
    private static final Class COACH_BUILDER = CoachBuilder.class;
    private static final Class EXERCISE_BUILDER = ExerciseBuilder.class;
    private static final Class PROGRAM_BUILDER = ProgramBuilder.class;
    private static final Class NUTRITION_BUILDER = NutritionBuilder.class;


    @Test(dataProvider = "dataForBuilderCreator")
    public void shouldCreateAndReturnAppropriateBuilder(Class Builer, String BuilderName) {
        Builder builder = BuilderFactory.create(BuilderName);

        Class<? extends Builder> builderClass = builder.getClass();
        assertEquals(Builer, builderClass);
    }

    @DataProvider(name = "dataForBuilderCreator")
    public Object[][] dataForBuilderCreator() {
        return new Object[][]{
                {CLIENT_BUILDER,CLIENT},
                {COACH_BUILDER,COACH},
                {EXERCISE_BUILDER,EXERCISE},
                {PROGRAM_BUILDER,PROGRAM},
                {NUTRITION_BUILDER,NUTRITION}
        };
    }
}