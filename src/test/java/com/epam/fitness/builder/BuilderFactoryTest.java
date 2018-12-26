package com.epam.fitness.builder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuilderFactoryTest {
    private static final String CLIENT = "client";
    private static final String COACH = "coach";

    @Test
    public void shouldCreateAndReturnClientBuilder() {
        Builder builder = BuilderFactory.create(CLIENT);

        Class<? extends Builder> builderClass = builder.getClass();
        assertEquals(ClientBuilder.class, builderClass);
    }

    @Test
    public void shouldCreateAndReturnCoachBuilder() {
        Builder builder = BuilderFactory.create(COACH);

        Class<? extends Builder> builderClass = builder.getClass();
        assertEquals(CoachBuilder.class, builderClass);
    }
}