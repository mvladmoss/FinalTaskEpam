package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ExerciseDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class DeleteExerciseCommand implements Command {

    private String profilePage = "/controller?command=show_client_exercises&client_id=";
    private String EXERCISE_DTO_ID = "exerciseDtoId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            setCLientId(request);
        }
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        long exerciseDtoId = Long.parseLong(request.getParameter(EXERCISE_DTO_ID));
        exerciseDtoService.deleteExercise(exerciseDtoId);
        return new CommandResult(profilePage,false);
    }

    private void setCLientId(HttpServletRequest request) throws ServiceException {
        long exerciseDtoId = Long.parseLong(request.getParameter(EXERCISE_DTO_ID));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByExerciseDtoId(exerciseDtoId);
        clientOptional.ifPresent(client -> profilePage+=client.getId());
    }
}
