package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Program;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.repository.ProgramRepository;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.program.ProgramById;

import java.util.List;
import java.util.Optional;

public class ProgramService {

    public Optional<Program> findProgramById(Long programID) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {

            ProgramRepository programRepository = repositoryCreator.getProgramRepository();
            ProgramById specification = new ProgramById(programID);
            Optional<Program> program = programRepository.queryForSingleResult(specification);
            ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
            List<ExerciseDto> exercises = exerciseDtoService.findExercisesByProgramId(programID);
            program.get().setExercises(exercises);
            return program;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
