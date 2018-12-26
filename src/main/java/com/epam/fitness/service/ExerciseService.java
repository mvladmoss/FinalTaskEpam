package com.epam.fitness.service;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.repository.ExerciseRepository;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;

import java.util.List;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class ExerciseService {
    public List<Exercise> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseRepository exerciseRepository = repositoryCreator.getExerciseRepository();
            return exerciseRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Exercise> findById(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseRepository exerciseRepository = repositoryCreator.getExerciseRepository();
            return exerciseRepository.findById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}