package com.epam.fitness.model.dto;

import com.epam.fitness.model.Exercise;

public class ExerciseDto {

    private Exercise exercise;
    private int repeatNumber;
    private int setNumber;
    private int numberTrainDay;

    public ExerciseDto(Exercise exercise, int repeatNumber, int setNumber,int numberTrainDay){
        this.exercise = exercise;
        this.repeatNumber = repeatNumber;
        this.setNumber = setNumber;
        this.numberTrainDay = numberTrainDay;
    }

    public ExerciseDto(){};

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(int repeatNumber) {
        this.repeatNumber = repeatNumber;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getNumberTrainDay() {
        return numberTrainDay;
    }

    public void setNumberTrainDay(int numberTrainDay) {
        this.numberTrainDay = numberTrainDay;
    }
}
