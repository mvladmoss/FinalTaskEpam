package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.service.ExerciseDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteExerciseCommand implements Command {

    private final static String PROFILE_PAGE = "/controller?command=show_client_program";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        long exerciseDtoId = Long.parseLong(request.getParameter("exerciseDtoId"));
        exerciseDtoService.deleteExercise(exerciseDtoId);
        return new CommandResult(PROFILE_PAGE,true);
    }
}
