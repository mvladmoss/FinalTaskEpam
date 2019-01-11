package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.model.Program;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.service.OrderInformationService;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.uitls.CurrentMembershipValidChecker;


public class ShowClientExercisesCommand implements Command {

    private final static String PROGRAM = "program";
    private final static String EXERCISE_PAGE = "/WEB-INF/clientExercises.jsp";
    private final static String IS_MEMBERSHIP_VALID = "is_membership_valid";
    private CurrentMembershipValidChecker membershipValidChecker = new CurrentMembershipValidChecker();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute("role"));
        Long clientId;
        if(role.equals("coach")){
            clientId = Long.valueOf(request.getParameter("id_coach's_client"));
        }
        else{
            clientId = (long) session.getAttribute("id");
        }
        if(!membershipValidChecker.isCurrentMembershipValid(clientId)){
            return new CommandResult(EXERCISE_PAGE,false);
        }else {
            System.out.println("Yes");
            request.setAttribute(IS_MEMBERSHIP_VALID, true);
        }
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
        return new CommandResult(EXERCISE_PAGE,false);
    }




}