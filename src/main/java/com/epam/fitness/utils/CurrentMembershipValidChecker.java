package com.epam.fitness.utils;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.service.OrderInformationService;

import java.util.Date;
import java.util.Optional;

public class CurrentMembershipValidChecker {

    public boolean isCurrentMembershipValid(Long clientId) throws ServiceException {
        OrderInformationService orderInformationService = new OrderInformationService();
        Optional<OrderInformation> orderInformationOptional = orderInformationService.findByClientId(clientId);
        return orderInformationOptional.filter(orderInformation ->
                isCurrentDateLessEndTrainDate(orderInformation.getTrainEndDate())).isPresent();
    }

    private boolean isCurrentDateLessEndTrainDate(Date endTrainDate){
        return new Date().before(endTrainDate);
    }
}
