package com.epam.fitness.service;

import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.OrderRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

public class OrderService {


    public Optional<OrderInformation> findByClientId(long id){
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setId(2);
        orderInformation.setCost(new BigDecimal(5));
        orderInformation.setPaymenData(Timestamp.valueOf("2018-12-04 04:02:02"));
        orderInformation.setTrainStartDate(Date.valueOf("2018-12-03"));
        orderInformation.setTrainEndDate(Date.valueOf("2019-01-04"));
        return Optional.of(orderInformation);
    }

}
