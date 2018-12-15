package com.epam.fitness.service;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Program;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.model.dto.ProgramDto;
import com.epam.fitness.repository.ProgramRepository;
import com.epam.fitness.repository.RepositoryException;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.program.ProgramByIdSpecification;

import java.util.List;
import java.util.Optional;

public class ProgramDtoService  {

    public Optional<ProgramDto> findProgramDtoById(Long programID) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {

            ProgramRepository programRepository = repositoryCreator.getProgramRepository();
            ProgramByIdSpecification specification = new ProgramByIdSpecification(programID);
            Optional<Program> program = programRepository.queryForSingleResult(specification);
            ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
            List<ExerciseDto> exercises = exerciseDtoService.findExercisesByProgramId(programID);
            return Optional.of(new ProgramDto(program.get(),exercises));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
