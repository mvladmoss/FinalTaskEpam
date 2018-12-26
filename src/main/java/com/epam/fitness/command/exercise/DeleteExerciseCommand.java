package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.model.Client;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ExerciseDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class DeleteExerciseCommand implements Command {

    private String profilePage = "/controller?command=show_client_exercises&client_id=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        if(session.getAttribute("role").equals("coach")){
            setCLientId(request);
        }
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        long exerciseDtoId = Long.parseLong(request.getParameter("exerciseDtoId"));
        exerciseDtoService.deleteExercise(exerciseDtoId);
        return new CommandResult(profilePage,false);
    }

    private void setCLientId(HttpServletRequest request) throws ServiceException {
        long exerciseDtoId = Long.parseLong(request.getParameter("exerciseDtoId"));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByExerciseDtoId(exerciseDtoId);
        clientOptional.ifPresent(client -> profilePage+=client.getId());
    }
}
