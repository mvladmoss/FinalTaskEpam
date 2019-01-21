package com.epam.fitness.service;

import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.OrderInformationRepository;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.order.LastOrderByClientId;

import java.util.Optional;import com.epam.fitness.exception.ServiceException;


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

    public void save(OrderInformation orderInformation) throws ServiceException {
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderInformationRepository orderInformationRepository = repositoryCreator.getOrderInformationRepository();
            try {
                orderInformationRepository.save(orderInformation);
            } catch (RepositoryException e) {
                throw new ServiceException(e.getMessage(),e);
            }
        }
    }
}
