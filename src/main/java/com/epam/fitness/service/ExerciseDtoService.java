package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.ExerciseDtoRepository;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.exercise.dto.ExerciseDtoByProgramIdSpecification;

import java.util.List;

public class ExerciseDtoService {

    public List<ExerciseDto> findExercisesByProgramId(Long programId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseDtoRepository exerciseDtoRepository = repositoryCreator.getExerciseDtoRepository();
            ExerciseDtoByProgramIdSpecification specification = new ExerciseDtoByProgramIdSpecification(programId);
            return exerciseDtoRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
