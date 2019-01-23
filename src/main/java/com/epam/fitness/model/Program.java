package com.epam.fitness.model;

public class Program implements Identifiable{

    private Long id;
    private Long nutritionId;
    private int trainsPerWeek;

    public Program(Long id, Long nutritionId, int trainsPerWeek) {
        this.id = id;
        this.nutritionId = nutritionId;
        this.trainsPerWeek = trainsPerWeek;
    }

    public Program(){}

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

    public int getTrainsPerWeek() { return trainsPerWeek; }

    public void setTrainsPerWeek(int trainsPerWeek) {
        this.trainsPerWeek = trainsPerWeek;
    }

}
