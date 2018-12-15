package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.OrderInformationRepository;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.order.LastOrderByClientIdSpecification;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Optional;

public class OrderInformationService {


    public Optional<OrderInformation> findByClientId(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            OrderInformationRepository orderInformationRepository = repositoryCreator.getOrderInformationRepository();
            LastOrderByClientIdSpecification specification = new LastOrderByClientIdSpecification(id);
            return orderInformationRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long addOrder(BigDecimal cost, Timestamp paymentData, Date endDate, long clientId) throws ServiceException {
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderInformationRepository orderInformationRepository = repositoryCreator.getOrderInformationRepository();
            OrderInformation orderInformation = new OrderInformation();
            //Make orderInforamtion as Builder
            orderInformation.setCost(cost);
            orderInformation.setPaymenData(paymentData);
            orderInformation.setTrainEndDate(endDate);
            orderInformation.setClientId(clientId);
            try {
                return orderInformationRepository.save(orderInformation);
            } catch (RepositoryException e) {
                throw new ServiceException(e.getMessage(),e);
            }
        }
    }



}
