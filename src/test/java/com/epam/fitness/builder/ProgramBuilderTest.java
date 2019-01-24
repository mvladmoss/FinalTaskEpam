package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.Program;
import com.epam.fitness.repository.database.constants.CoachTableConstants;
import com.epam.fitness.repository.database.constants.ProgramTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProgramBuilderTest {

    private static final Long ID_PROGRAM = 1L;
    private static final Long NUTRITION_ID = 2L;
    private static final Integer TRAINS_PER_WEEK = 3;

    private static final Program EXPECTED_PROGRAM = new Program(
            ID_PROGRAM,
            NUTRITION_ID,
            TRAINS_PER_WEEK);

    @Test
    public void shouldBuildAndReturnProgramWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(ProgramTableConstants.ID.getFieldName())).thenReturn(ID_PROGRAM);
        when(resultSet.getLong(ProgramTableConstants.NUTRITION_ID.getFieldName())).thenReturn(NUTRITION_ID);
        when(resultSet.getInt(ProgramTableConstants.TRAINS_PER_WEEK.getFieldName())).thenReturn(TRAINS_PER_WEEK);

        ProgramBuilder programBuilder = new ProgramBuilder();
        Program actualProgram = programBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_PROGRAM, actualProgram);

    }
}
