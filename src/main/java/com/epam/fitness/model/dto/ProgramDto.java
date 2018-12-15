package com.epam.fitness.model.dto;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.Program;

import java.util.List;

public class ProgramDto {

    private Program program;
    private List<ExerciseDto> exercises;

    public ProgramDto(Program program, List<ExerciseDto> exercises){
        this.program = program;
        this.exercises = exercises;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<ExerciseDto> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDto> exercises) {
        this.exercises = exercises;
    }

}
