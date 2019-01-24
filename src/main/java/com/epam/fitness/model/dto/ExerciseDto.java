package com.epam.fitness.model.dto;

import com.epam.fitness.model.Coach;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.Identifiable;

import java.util.Objects;

public class ExerciseDto implements Identifiable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExerciseDto exerciseDto = (ExerciseDto) o;
        return  Objects.equals(getId(), exerciseDto.getId()) &&
                getExercise().equals(exerciseDto.getExercise()) &&
                Objects.equals(getProgramId(), exerciseDto.getProgramId()) &&
                Objects.equals(getRepeatNumber(), exerciseDto.getRepeatNumber()) &&
                Objects.equals(getSetNumber(), exerciseDto.getSetNumber()) &&
                Objects.equals(getNumberTrainDay(), exerciseDto.getNumberTrainDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getExercise(),getProgramId(),getRepeatNumber(),getSetNumber(),getNumberTrainDay());
    }
}
