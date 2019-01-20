package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ExerciseDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class UpdateExerciseCommand implements Command {

    private String profilePage = "/controller?command=show_client_exercises&client_id=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        updateExerciseDto(request);
        HttpSession session = request.getSession();
        if(session.getAttribute("role").equals("coach")){
            setClientId(request);
        }
        return new CommandResult(profilePage,true);
    }

    private void updateExerciseDto(HttpServletRequest request) throws ServiceException {
        Long exerciseDtoId = Long.valueOf(request.getParameter("exerciseDtoId"));
        int repeats = Integer.parseInt(request.getParameter("repeats"));
        int setNumber = Integer.parseInt(request.getParameter("setNumber"));
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        Optional<ExerciseDto> exerciseDto = exerciseDtoService.findById(exerciseDtoId);
        exerciseDto.get().setSetNumber(setNumber);
        exerciseDto.get().setRepeatNumber(repeats);
        exerciseDtoService.save(exerciseDto.get());
    }

    private void setClientId(HttpServletRequest request) throws ServiceException {
        long exerciseDtoId = Long.parseLong(request.getParameter("exerciseDtoId"));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByExerciseDtoId(exerciseDtoId);
        clientOptional.ifPresent(client -> profilePage+=client.getId());
    }
}
