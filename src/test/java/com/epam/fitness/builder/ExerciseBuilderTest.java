package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.repository.database.constants.ClientTableConstants;
import com.epam.fitness.repository.database.constants.ExerciseDtoTableConstants;
import com.epam.fitness.repository.database.constants.ExerciseTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExerciseBuilderTest {

    private static final Long ID_EXERCISE = 1L;
    private static final String NAME = "Vlad";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "form.jpg";

    private static final Exercise EXPECTED_EXERCISE = new Exercise(
            ID_EXERCISE,
            NAME,
            DESCRIPTION,
            IMAGE);

    @Test
    public void shouldBuildAndReturnExerciseWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(ExerciseTableConstants.ID.getFieldName())).thenReturn(ID_EXERCISE);
        when(resultSet.getString(ExerciseTableConstants.NAME.getFieldName())).thenReturn(NAME);
        when(resultSet.getString(ExerciseTableConstants.DESCRIPTION.getFieldName())).thenReturn(DESCRIPTION);
        when(resultSet.getString(ExerciseTableConstants.IMAGE.getFieldName())).thenReturn(IMAGE);

        ExerciseBuilder exerciseBuilder = new ExerciseBuilder();
        Exercise actualExercise = exerciseBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_EXERCISE, actualExercise);

    }
}
