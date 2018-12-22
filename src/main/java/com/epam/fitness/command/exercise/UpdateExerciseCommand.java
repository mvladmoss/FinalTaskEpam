package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.service.ExerciseDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UpdateExerciseCommand implements Command {

    private final static String PROFILE_PAGE = "/controller?command=show_client_program";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long exerciseDtoId = Long.valueOf(request.getParameter("exerciseDtoId"));
        int repeats = Integer.parseInt(request.getParameter("repeats"));
        int setNumber = Integer.parseInt(request.getParameter("setNumber"));
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        System.out.println(exerciseDtoId);
        Optional<ExerciseDto> exerciseDto = exerciseDtoService.findById(exerciseDtoId);
        exerciseDto.get().setSetNumber(setNumber);
        exerciseDto.get().setRepeatNumber(repeats);
        exerciseDtoService.updateExercise(exerciseDto.get());
        return new CommandResult(PROFILE_PAGE,true);
    }
}
