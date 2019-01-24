package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.repository.database.constants.ClientTableConstants;
import com.epam.fitness.repository.database.constants.CoachTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoachBuilderTest {

    private static final Long ID_COACH = 1L;
    private static final String NAME = "Vlad";
    private static final String SURNAME = "Moskovkin";
    private static final String LOGIN = "vladmoss";
    private static final String PASSWORD = "123";

    private static final Coach EXPECTED_COACH = new Coach(
            ID_COACH,
            NAME,
            SURNAME,
            LOGIN,
            PASSWORD);

    @Test
    public void shouldBuildAndReturnCoachWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(CoachTableConstants.ID.getFieldName())).thenReturn(ID_COACH);
        when(resultSet.getString(CoachTableConstants.NAME.getFieldName())).thenReturn(NAME);
        when(resultSet.getString(CoachTableConstants.SURNAME.getFieldName())).thenReturn(SURNAME);
        when(resultSet.getString(CoachTableConstants.LOGIN.getFieldName())).thenReturn(LOGIN);
        when(resultSet.getString(CoachTableConstants.PASSWORD.getFieldName())).thenReturn(PASSWORD);

        CoachBuilder coachBuilder = new CoachBuilder();
        Coach actualCoach = coachBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_COACH, actualCoach);

    }
}
