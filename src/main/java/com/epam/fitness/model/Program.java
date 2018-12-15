package com.epam.fitness.model;

import com.epam.fitness.model.dto.ExerciseDto;

import java.util.List;

public class Program {

    private Long id;
    private Long nutritionId;
    private String name;
    private String description;
    private int trainsPerWeek;
    List<ExerciseDto> exercises;


    public List<ExerciseDto> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDto> exercises) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTrainsPerWeek() {
        return trainsPerWeek;
    }

    public void setTrainsPerWeek(int trainsPerWeek) {
        this.trainsPerWeek = trainsPerWeek;
    }

}
