package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.ExerciseDtoRepository;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.exercise.dto.ExerciseDtoById;
import com.epam.fitness.repository.specifications.exercise.dto.ExerciseDtoByProgramId;

import java.util.List;
import java.util.Optional;

public class ExerciseDtoService {

    public Optional<ExerciseDto> findById(long id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseDtoRepository exerciseDtoRepository = repositoryCreator.getExerciseDtoRepository();
            ExerciseDtoById specification = new ExerciseDtoById(id);
            return exerciseDtoRepository.queryForSingleResult(specification);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<ExerciseDto> findExercisesByProgramId(Long programId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseDtoRepository exerciseDtoRepository = repositoryCreator.getExerciseDtoRepository();
            ExerciseDtoByProgramId specification = new ExerciseDtoByProgramId(programId);
            return exerciseDtoRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public long addExercise(ExerciseDto exerciseDto) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseDtoRepository exerciseDtoRepository = repositoryCreator.getExerciseDtoRepository();
            return exerciseDtoRepository.save(exerciseDto);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public long deleteExercise(long exerciseDtoId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseDtoRepository exerciseDtoRepository = repositoryCreator.getExerciseDtoRepository();
            return exerciseDtoRepository.delete(exerciseDtoId);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public long updateExercise(ExerciseDto exerciseDto) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ExerciseDtoRepository exerciseDtoRepository = repositoryCreator.getExerciseDtoRepository();
            return exerciseDtoRepository.save(exerciseDto);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
