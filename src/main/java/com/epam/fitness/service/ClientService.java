package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.ClientRepository;
import com.epam.fitness.repository.specifications.client.CLientByProgramId;
import com.epam.fitness.repository.specifications.client.ClientByLoginAndPassword;
import com.epam.fitness.repository.specifications.client.ClientByNutritionId;
import com.epam.fitness.repository.specifications.client.ClientsByCoachId;
import com.sun.javafx.image.IntPixelGetter;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class ClientService {

    public Optional<Client> login(String login, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            ClientByLoginAndPassword specification = new ClientByLoginAndPassword(login, password);
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

    public List<Client> findByCoachId(long coachId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            ClientsByCoachId specification = new ClientsByCoachId(coachId);
            return clientRepository.query(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Optional<Client> findByProgramId(long programId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            CLientByProgramId specification = new CLientByProgramId(programId);
            return clientRepository.queryForSingleResult(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Optional<Client> findByExerciseDtoId(long exerciseDtoId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
            Optional<ExerciseDto> exerciseDto = exerciseDtoService.findById(exerciseDtoId);
            long programId = exerciseDto.get().getProgramId();
            CLientByProgramId specification = new CLientByProgramId(programId);
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            return clientRepository.queryForSingleResult(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Optional<Client> findByNutritionId(long nutritionId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientByNutritionId specification = new ClientByNutritionId(nutritionId);
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            return clientRepository.queryForSingleResult(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Long save(Client client) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            return clientRepository.save(client);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Long getNextIdInTable() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            return clientRepository.getNextTableId();
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

}

