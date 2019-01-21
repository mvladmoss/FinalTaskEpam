package com.epam.fitness.builder;

import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.database.constants.OrderInformationTableConstants;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class OrderInformationBuilder implements Builder<OrderInformation> {
    @Override
    public OrderInformation build(ResultSet resultSet) throws RepositoryException {
        OrderInformation orderInformation = new OrderInformation();
        try {
            Long orderID = resultSet.getLong(OrderInformationTableConstants.ID.getFieldName());
            orderInformation.setId(orderID);
            BigDecimal cost = resultSet.getBigDecimal(OrderInformationTableConstants.COST.getFieldName());
            orderInformation.setCost(cost);
            Timestamp paymentData = resultSet.getTimestamp(OrderInformationTableConstants.PAYMENT_DATA.getFieldName());
            orderInformation.setPaymentData(paymentData);
            Date membership_end_date = resultSet.getDate(OrderInformationTableConstants.MEMBERSHIP_END_DATE.getFieldName());
            orderInformation.setMembershipEndDate(membership_end_date);
            return orderInformation ;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
