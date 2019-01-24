package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Client;
import com.epam.fitness.repository.database.constants.ClientTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientBuilderTest {

    private static final Long ID_CLIENT = 1L;
    private static final Long ID_COACH = 3L;
    private static final String NAME = "Vlad";
    private static final String SURNAME = "Moskovkin";
    private static final String LOGIN = "vladmoss";
    private static final String PASSWORD = "123";
    private static final Integer MEMBERSHIP_PURCHASED_NUMBER = 1;
    private static final Float PERSONAL_DISCOUNT = 5.5f;
    private static final Long PROGRAM_ID = 5L;



    private static final Client EXPECTED_CLIENT = new Client(
            ID_CLIENT,
            ID_COACH,
            NAME,
            SURNAME,
            LOGIN,
            PASSWORD,
            MEMBERSHIP_PURCHASED_NUMBER,
            PERSONAL_DISCOUNT,
            PROGRAM_ID);

    @Test
    public void shouldBuildAndReturnClientWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(ClientTableConstants.ID.getFieldName())).thenReturn(ID_CLIENT);
        when(resultSet.getLong(ClientTableConstants.COACH_ID.getFieldName())).thenReturn(ID_COACH);
        when(resultSet.getString(ClientTableConstants.NAME.getFieldName())).thenReturn(NAME);
        when(resultSet.getString(ClientTableConstants.SURNAME.getFieldName())).thenReturn(SURNAME);
        when(resultSet.getString(ClientTableConstants.LOGIN.getFieldName())).thenReturn(LOGIN);
        when(resultSet.getString(ClientTableConstants.PASSWORD.getFieldName())).thenReturn(PASSWORD);
        when(resultSet.getInt(ClientTableConstants.MEMBERSHIP_PURCHASED_NUMBER.getFieldName())).thenReturn(MEMBERSHIP_PURCHASED_NUMBER);
        when(resultSet.getFloat(ClientTableConstants.PERSONAL_DISCOUNT.getFieldName())).thenReturn(PERSONAL_DISCOUNT);
        when(resultSet.getLong(ClientTableConstants.PROGRAM_ID.getFieldName())).thenReturn(PROGRAM_ID);

        ClientBuilder clientBuilder = new ClientBuilder();
        Client actualClient = clientBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_CLIENT, actualClient);

    }
}
