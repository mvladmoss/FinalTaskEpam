package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.service.ExerciseDtoService;
import com.epam.fitness.service.ExerciseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AddExerciseCommand implements Command {

    private final static String PROFILE_PAGE = "/controller?command=show_client_program";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ExerciseDto exerciseDto = makeExercise(request);
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        exerciseDtoService.addExercise(exerciseDto);
        return new CommandResult(PROFILE_PAGE,true);
    }

    private ExerciseDto makeExercise(HttpServletRequest request) throws ServiceException {
        ExerciseDto exerciseDto = new ExerciseDto();
        int repeats = Integer.parseInt(request.getParameter("repeats"));
        exerciseDto.setRepeatNumber(repeats);
        int setNumber = Integer.parseInt(request.getParameter("setNumber"));
        exerciseDto.setSetNumber(setNumber);
        int trainDay = Integer.parseInt(request.getParameter("trainDay"));
        System.out.println(trainDay);
        exerciseDto.setNumberTrainDay(trainDay);
        long programId = Long.parseLong(request.getParameter("programId"));
        exerciseDto.setProgramId(programId);
        ExerciseService service = new ExerciseService();
        long exerciseId = Long.parseLong(request.getParameter("exerciseId"));
        Optional<Exercise> exercise = service.findById(exerciseId);
        exerciseDto.setExercise(exercise.get());
        return exerciseDto;
    }
}
