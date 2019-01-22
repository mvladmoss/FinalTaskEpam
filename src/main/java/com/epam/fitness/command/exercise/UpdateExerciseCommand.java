package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ExerciseDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;
import com.sun.org.apache.regexp.internal.RE;


public class UpdateExerciseCommand implements Command {

    private final static String PROFILE_PAGE = "/controller?command=show_client_exercises";
    private final static String REPEATS = "repeats";
    private final static String SET_NUMBER = "setNumber";
    private final static String EXERCISE_DTO_ID = "exerciseDtoId";
    private final static String COACH_CLIENT_ID = "coach_client_id";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        updateExerciseDto(request);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            setClientId(request);
        }
        return new CommandResult(PROFILE_PAGE,true);
    }

    private void updateExerciseDto(HttpServletRequest request) throws ServiceException {
        Long exerciseDtoId = Long.valueOf(request.getParameter(EXERCISE_DTO_ID));
        int repeats = Integer.parseInt(request.getParameter(REPEATS));
        int setNumber = Integer.parseInt(request.getParameter(SET_NUMBER));
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        Optional<ExerciseDto> exerciseDto = exerciseDtoService.findById(exerciseDtoId);
        exerciseDto.get().setSetNumber(setNumber);
        exerciseDto.get().setRepeatNumber(repeats);
        exerciseDtoService.save(exerciseDto.get());
    }

    private void setClientId(HttpServletRequest request) throws ServiceException {
        Long exerciseDtoId = Long.parseLong(request.getParameter(EXERCISE_DTO_ID));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByExerciseDtoId(exerciseDtoId);
        clientOptional.ifPresent(client -> {
            HttpSession session = request.getSession();
            session.setAttribute(COACH_CLIENT_ID,client.getId());
        });
    }
}
