package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.database.constants.ExerciseDtoTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseDtoBuilder implements Builder<ExerciseDto> {
    @Override
    public ExerciseDto build(ResultSet resultSet) throws RepositoryException {
        Exercise exercise = new ExerciseBuilder().build(resultSet);
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setExercise(exercise);
        try {
            long id = resultSet.getLong(ExerciseDtoTableConstants.ID.getFieldName());
            exerciseDto.setId(id);
            int repeatNumber = resultSet.getInt(ExerciseDtoTableConstants.REPEAT_NUMBER.getFieldName());
            exerciseDto.setRepeatNumber(repeatNumber);
            int setNumber = resultSet.getInt(ExerciseDtoTableConstants.SET_NUMBER.getFieldName());
            exerciseDto.setSetNumber(setNumber);
            int numberOfTrainDay = resultSet.getInt(ExerciseDtoTableConstants.NUMBER_TRAIN_DAY.getFieldName());
            exerciseDto.setNumberTrainDay(numberOfTrainDay);
            long program_id = resultSet.getLong(ExerciseDtoTableConstants.PROGRAM_ID.getFieldName());
            exerciseDto.setProgramId(program_id);
            return exerciseDto;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
