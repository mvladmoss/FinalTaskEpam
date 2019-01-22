package com.epam.fitness.model;

import com.epam.fitness.model.dto.ExerciseDto;

import java.util.List;

public class Program implements Identifiable{

    private Long id;
    private Long nutritionId;
    private String name;
    private int trainsPerWeek;
    private List<ExerciseDto> exercises;

    public Program(Long id, Long nutritionId, String name, int trainsPerWeek, List<ExerciseDto> exercises) {
        this.id = id;
        this.nutritionId = nutritionId;
        this.name = name;
        this.trainsPerWeek = trainsPerWeek;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(Long nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrainsPerWeek() {
        return trainsPerWeek;
    }

    public void setTrainsPerWeek(int trainsPerWeek) {
        this.trainsPerWeek = trainsPerWeek;
    }

    public List<ExerciseDto> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDto> exercises) {
        this.exercises = exercises;
    }
}
