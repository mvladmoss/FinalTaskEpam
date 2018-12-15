package com.epam.fitness.builder;

import com.epam.fitness.model.OrderInformation;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class OrderInformationBuilder implements Builder<OrderInformation> {
    @Override
    public OrderInformation build(ResultSet resultSet) {
        OrderInformation orderInformation = new OrderInformation();
        try {
            Long orderID = resultSet.getLong("id_order_information");
            orderInformation.setId(orderID);
            BigDecimal cost = resultSet.getBigDecimal("cost");
            orderInformation.setCost(cost);
            Timestamp paymentData = resultSet.getTimestamp("payment_data");
            orderInformation.setPaymenData(paymentData);
            Date end_date = resultSet.getDate("end_date");
            orderInformation.setTrainEndDate(end_date);
        }catch (SQLException exception){
            //Log
            throw new RuntimeException();
        }
        return orderInformation ;
    }
}
