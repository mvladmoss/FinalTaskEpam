package com.epam.fitness.builder;

import com.epam.fitness.builder.resultset.BuilderFactory;
import com.epam.fitness.builder.resultset.ClientBuilder;
import com.epam.fitness.builder.resultset.CoachBuilder;
import com.epam.fitness.builder.resultset.ResultSetBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuilderFactoryTest {
    private static final String CLIENT = "client";
    private static final String COACH = "coach";

    @Test
    public void shouldCreateAndReturnClientBuilder() {
        ResultSetBuilder builder = BuilderFactory.create(CLIENT);

        Class<? extends ResultSetBuilder> builderClass = builder.getClass();
        assertEquals(ClientBuilder.class, builderClass);
    }

    @Test
    public void shouldCreateAndReturnCoachBuilder() {
        ResultSetBuilder builder = BuilderFactory.create(COACH);

        Class<? extends ResultSetBuilder> builderClass = builder.getClass();
        assertEquals(CoachBuilder.class, builderClass);
    }
}