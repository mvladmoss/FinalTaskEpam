package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.ClientRepository;
import com.epam.fitness.repository.specifications.client.ClientByLoginAndPasswordSpecification;
import com.epam.fitness.repository.specifications.client.ClientsByCoachIdSpecification;

import java.util.List;
import java.util.Optional;

public class ClientService {

    public Optional<Client> login(String login, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            ClientByLoginAndPasswordSpecification specification = new ClientByLoginAndPasswordSpecification(login, password);
            return clientRepository.queryForSingleResult(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Optional<Client> findById(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            return clientRepository.findById(id);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<Client> findByCoachId(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            ClientsByCoachIdSpecification specification = new ClientsByCoachIdSpecification(id);
            return clientRepository.query(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public long addCoach(Client client, long coachId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            client.setCoachId(coachId);
            return clientRepository.save(client);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}

