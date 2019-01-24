package com.epam.fitness.command.exercise.validator;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.service.ExerciseDtoService;
import com.epam.fitness.utils.RequestParameterValidator;

import java.util.Optional;

public class ParameterValidator {

    private RequestParameterValidator validator = new RequestParameterValidator();

    public boolean isRepeatsValid(String repeats){
        return repeats != null && validator.isRepeatsValid(repeats);
    }

    public boolean isSetNumberValid(String setNumber){
        return setNumber != null && validator.isSetNumberValid(setNumber);
    }

    public boolean isIdentifiableIdValid(String exerciseDtoIdString){
        return exerciseDtoIdString != null && validator.isIdentifiableIdValid(exerciseDtoIdString);
    }

    public boolean isExerciseExist(Long exerciseId) throws ServiceException {
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        Optional<ExerciseDto> exerciseDto = exerciseDtoService.findById(exerciseId);
        return exerciseDto.isPresent();
    }

}
