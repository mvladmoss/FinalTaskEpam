package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.database.constants.CoachTableConstants;
import com.epam.fitness.repository.database.constants.ExerciseDtoTableConstants;
import com.epam.fitness.repository.database.constants.ExerciseTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExerciseDtoBuilderTest {

    private static final Long ID_EXERCISE_DTO = 1L;
    private static final Long PROGRAM_ID = 1L;
    private static final Long EXERCISE_ID = 1L;
    private static final Exercise EXERCISE = new Exercise(EXERCISE_ID) ;
    private static final Integer REPEAT_NUMBER = 2;
    private static final Integer SET_NUMBER = 22;
    private static final Integer NUMBER_TRAIN_DAY = 1;

    private static final ExerciseDto EXPECTED_EXERCISE_DTO = new ExerciseDto(
            ID_EXERCISE_DTO,
            EXERCISE,
            PROGRAM_ID,
            REPEAT_NUMBER,
            SET_NUMBER,
            NUMBER_TRAIN_DAY);

    @Test
    public void shouldBuildAndReturnExerciseDtoWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(ExerciseDtoTableConstants.ID.getFieldName())).thenReturn(ID_EXERCISE_DTO);
        when(resultSet.getLong(ExerciseTableConstants.ID.getFieldName())).thenReturn(EXERCISE_ID);
        when(resultSet.getLong(ExerciseDtoTableConstants.PROGRAM_ID.getFieldName())).thenReturn(PROGRAM_ID);
        when(resultSet.getInt(ExerciseDtoTableConstants.REPEAT_NUMBER.getFieldName())).thenReturn(REPEAT_NUMBER);
        when(resultSet.getInt(ExerciseDtoTableConstants.SET_NUMBER.getFieldName())).thenReturn(SET_NUMBER);
        when(resultSet.getInt(ExerciseDtoTableConstants.NUMBER_TRAIN_DAY.getFieldName())).thenReturn(NUMBER_TRAIN_DAY);

        ExerciseDtoBuilder exerciseDtoBuilder = new ExerciseDtoBuilder();
        ExerciseDto actualExerciseDto = exerciseDtoBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_EXERCISE_DTO, actualExerciseDto);

    }
}
