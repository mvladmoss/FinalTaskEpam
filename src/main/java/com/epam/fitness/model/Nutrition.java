package com.epam.fitness.model;

public class Nutrition implements Identifiable {

    private Long id;
    private String name;
    private String description;
    private String morningNutrition;
    private String lunchNutrition;
    private String dinnerNutrition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMorningNutrition() {
        return morningNutrition;
    }

    public void setMorningNutrition(String morningNutrition) {
        this.morningNutrition = morningNutrition;
    }

    public String getLunchNutrition() {
        return lunchNutrition;
    }

    public void setLunchNutrition(String lunchNutrition) {
        this.lunchNutrition = lunchNutrition;
    }

    public String getDinnerNutrition() {
        return dinnerNutrition;
    }

    public void setDinnerNutrition(String dinnerNutrition) {
        this.dinnerNutrition = dinnerNutrition;
    }
}
