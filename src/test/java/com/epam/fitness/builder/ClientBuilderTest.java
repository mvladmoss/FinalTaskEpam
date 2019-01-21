package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Client;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientBuilderTest {

    private static final Long ID_CLIENT = 1l;
    private static final Long ID_COACH = 3l;
    private static final String NAME = "Vlad";
    private static final String SURNAME = "Moskovkin";
    private static final String LOGIN = "vladmoss";
    private static final String PASSWORD = "123";
    private static final Integer MEMBERSHIP_PURCHASED_NUMBER = 1;
    private static final Float PERSONAL_SALE = 5.5f;
    private static final Float CORPORATE_SALE = 0.0f;
    private static final Long ID_PROGRAM = 5l;



    private static final Client EXPECTED_CLIENT = new Client(
            ID_CLIENT,
            ID_COACH,
            NAME,
            SURNAME,
            LOGIN,
            PASSWORD,
            MEMBERSHIP_PURCHASED_NUMBER,
            PERSONAL_SALE,
            CORPORATE_SALE,
            ID_PROGRAM);

    @Test
    public void shouldBuildAndReturnClientWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(Client.ID)).thenReturn(ID_CLIENT);
        when(resultSet.getLong(Client.ID_COACH)).thenReturn(ID_COACH);
        when(resultSet.getString(Client.NAME)).thenReturn(NAME);
        when(resultSet.getString(Client.SURNAME)).thenReturn(SURNAME);
        when(resultSet.getString(Client.LOGIN)).thenReturn(LOGIN);
        when(resultSet.getString(Client.PASSWORD)).thenReturn(PASSWORD);
        when(resultSet.getInt(Client.MEMBERSHIP_PURCHASED_NUMBER)).thenReturn(MEMBERSHIP_PURCHASED_NUMBER);
        when(resultSet.getFloat(Client.PERSONAL_DISCOUNT)).thenReturn(PERSONAL_SALE);
        when(resultSet.getFloat(Client.CORPORATE_DISCOUNT)).thenReturn(CORPORATE_SALE);
        when(resultSet.getLong(Client.ID_PROGRAM)).thenReturn(ID_PROGRAM);

        ClientBuilder clientBuilder = new ClientBuilder();
        Client actualClient = clientBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_CLIENT, actualClient);

    }
}
