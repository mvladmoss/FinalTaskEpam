package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.SqlSpecification;
import com.epam.fitness.repository.ClientRepository;
import com.epam.fitness.repository.specifications.client.ClientInfoById;
import com.epam.fitness.repository.specifications.client.ClientInfoByLogin;

import java.util.Optional;

public class ClientService {

    public Optional<Client> login(String login, String password) throws ServiceException {
        /*Client client = new Client();
        client.setName("vlad");
        client.setSurname("moskovkin");
        client.setClientID(2);
        client.setCoachId(1);
        client.setLogin("vladmoss");
        client.setPassword("1233");
        client.setVisitNumber(2);
        SqlSpecification specification = new ClientInfoByLogin(login);
        return Optional.of(client);*/
        /*try {
//            return  repository.executeQueryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }*/
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            return clientRepository.findClientByLogin(login, password);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    public Optional<Client> findById(long id) throws ServiceException {
        Client client = new Client();
        client.setName("vlad");
        client.setSurname("moskovkin");
        client.setClientID(2);
        client.setCoachId(1);
        client.setLogin("vladmoss");
        client.setPassword("1233");
        client.setVisitNumber(2);
        SqlSpecification specification = new ClientInfoById(id);
        return Optional.of(client);
        /*try {
//            return  repository.executeQueryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }*/
    }

}
