package com.epam.fitness.builder;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.dto.ExerciseDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseDtoBuilder implements Builder<ExerciseDto> {
    @Override
    public ExerciseDto build(ResultSet resultSet) {
        Exercise exercise = new ExerciseBuilder().build(resultSet);
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setExercise(exercise);
        try {
            long id = resultSet.getLong("id");
            exerciseDto.setId(id);
            int repeatNumber = resultSet.getInt("repeat_number");
            exerciseDto.setRepeatNumber(repeatNumber);
            int setNumber = resultSet.getInt("set_number");
            exerciseDto.setSetNumber(setNumber);
            int numberOfTrainDay = resultSet.getInt("number_train_day");
            exerciseDto.setNumberTrainDay(numberOfTrainDay);
            long program_id = resultSet.getLong("program_id");
            exerciseDto.setProgramId(program_id);
        }catch (SQLException exception){
            throw new IllegalArgumentException(exception);
        }
        return exerciseDto;
    }
}
