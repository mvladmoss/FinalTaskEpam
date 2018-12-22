package com.epam.fitness.model.dto;

import com.epam.fitness.model.Exercise;

public class ExerciseDto {

    private Long id;
    private Exercise exercise;
    private int repeatNumber;
    private int setNumber;
    private Long programId;
    private int numberTrainDay;

    public ExerciseDto(Long id, Exercise exercise, Long programId, int repeatNumber, int setNumber,int numberTrainDay){
        this.id = id;
        this.exercise = exercise;
        this.programId = programId;
        this.repeatNumber = repeatNumber;
        this.setNumber = setNumber;
        this.numberTrainDay = numberTrainDay;
    }

    public ExerciseDto(){}

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }
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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
