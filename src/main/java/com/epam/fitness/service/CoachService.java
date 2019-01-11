package com.epam.fitness.service;

import com.epam.fitness.model.Coach;
import com.epam.fitness.repository.CoachRepository;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.coach.CoachByClientId;
import com.epam.fitness.repository.specifications.coach.CoachByLoginAndPassword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class CoachService  {


    public Optional<Coach> login(String login, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CoachRepository coachRepository = repositoryCreator.getCoachRepository();
            CoachByLoginAndPassword specification = new CoachByLoginAndPassword(login, password);
            return coachRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Coach> findById(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CoachRepository coachRepository = repositoryCreator.getCoachRepository();
            return coachRepository.findById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Coach> findByClientId(long clientId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CoachRepository coachRepository = repositoryCreator.getCoachRepository();
            CoachByClientId specification = new CoachByClientId(clientId);
            return coachRepository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Coach> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            CoachRepository coachRepository = repositoryCreator.getCoachRepository();
            return coachRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }


}
