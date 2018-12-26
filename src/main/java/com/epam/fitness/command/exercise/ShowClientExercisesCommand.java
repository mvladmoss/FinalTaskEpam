package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.Program;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class ShowClientExercisesCommand implements Command {

    private final static String PROGRAM = "program";
    private final static String EXERCISES = "exercises";
    private final static String EXERCISE_PAGE = "/WEB-INF/clientExercises.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute("role"));
        Long clientId;
        if(role.equals("coach")){
            clientId = Long.valueOf(request.getParameter("client_id"));
        }
        else{
            clientId = (long) session.getAttribute("id");
        }
        System.out.println(clientId);
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientId);
        if(client.isPresent()) {
            Optional<Long> programId = Optional.of(client.get().getProgramId());
            if (programId.isPresent()) {
                ProgramService programService = new ProgramService();
                Optional<Program> program = programService.findProgramById(programId.get());
                request.setAttribute(PROGRAM, program.get());
            }
        }
        ExerciseService exerciseService = new ExerciseService();
        List<Exercise> exercises = exerciseService.findAll();
        request.setAttribute(EXERCISES,exercises);
        return new CommandResult(EXERCISE_PAGE,false);
    }
}
