package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.OrderInformationRepository;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.order.LastOrderByClientId;

import java.util.Optional;

public class OrderInformationService {


    public Optional<OrderInformation> findByClientId(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            OrderInformationRepository orderInformationRepository = repositoryCreator.getOrderInformationRepository();
            LastOrderByClientId specification = new LastOrderByClientId(id);
            return orderInformationRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long addOrder(OrderInformation orderInformation) throws ServiceException {
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderInformationRepository orderInformationRepository = repositoryCreator.getOrderInformationRepository();
            try {
                return orderInformationRepository.save(orderInformation);
            } catch (RepositoryException e) {
                throw new ServiceException(e.getMessage(),e);
            }
        }
    }



}
