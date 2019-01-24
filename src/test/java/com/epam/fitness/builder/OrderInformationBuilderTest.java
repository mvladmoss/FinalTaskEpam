package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.database.constants.CoachTableConstants;
import com.epam.fitness.repository.database.constants.OrderInformationTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderInformationBuilderTest {

    private static final Long ID_ORDER = 1L;
    private static final BigDecimal COST = BigDecimal.valueOf(1232.43);
    private static final Timestamp PAYMENT_DATA = new Timestamp(new Date().getTime());
    private static final Date MEMBERSHIP_END_DATE = new Date();
    private static final Long CLIENT_ID = 3L;
    private static final String CARD_NUMBER = "1234123412341234";

    private static final OrderInformation EXPECTED_ORDER = new OrderInformation(
            ID_ORDER,
            COST,
            PAYMENT_DATA,
            MEMBERSHIP_END_DATE,
            CLIENT_ID,
            CARD_NUMBER);

    @Test
    public void shouldBuildAndReturnOrderInformationWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(OrderInformationTableConstants.ID.getFieldName())).thenReturn(ID_ORDER);
        when(resultSet.getBigDecimal(OrderInformationTableConstants.COST.getFieldName())).thenReturn(COST);
        when(resultSet.getTimestamp(OrderInformationTableConstants.PAYMENT_DATA.getFieldName())).thenReturn(PAYMENT_DATA);
        when(resultSet.getDate(OrderInformationTableConstants.MEMBERSHIP_END_DATE.getFieldName())).thenReturn(new java.sql.Date(MEMBERSHIP_END_DATE.getTime()));
        when(resultSet.getLong(OrderInformationTableConstants.CLIENT_ID.getFieldName())).thenReturn(CLIENT_ID);
        when(resultSet.getString(OrderInformationTableConstants.CARD_NUMBER.getFieldName())).thenReturn(CARD_NUMBER);

        OrderInformationBuilder orderInformationBuilder = new OrderInformationBuilder();
        OrderInformation actualOrder = orderInformationBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_ORDER, actualOrder);

    }
}
