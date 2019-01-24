package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.database.constants.ExerciseDtoTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseDtoBuilder implements ResultSetBuilder<ExerciseDto> {
    @Override
    public ExerciseDto build(ResultSet resultSet) throws RepositoryException {
        Exercise exercise = new ExerciseBuilder().build(resultSet);
        try {
            long id = resultSet.getLong(ExerciseDtoTableConstants.ID.getFieldName());
            int repeatNumber = resultSet.getInt(ExerciseDtoTableConstants.REPEAT_NUMBER.getFieldName());
            int setNumber = resultSet.getInt(ExerciseDtoTableConstants.SET_NUMBER.getFieldName());
            int numberOfTrainDay = resultSet.getInt(ExerciseDtoTableConstants.NUMBER_TRAIN_DAY.getFieldName());
            long programId = resultSet.getLong(ExerciseDtoTableConstants.PROGRAM_ID.getFieldName());
            return new ExerciseDto(id,exercise,programId,repeatNumber,setNumber,numberOfTrainDay);
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
