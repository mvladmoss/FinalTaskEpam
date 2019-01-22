package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Program;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.utils.CurrentMembershipValidChecker;


public class ShowClientExercisesCommand implements Command {

    private final static String PROGRAM = "program";
    private final static String EXERCISE_PAGE = "/WEB-INF/client/clientExercise.jsp";
    private final static String IS_MEMBERSHIP_VALID = "is_membership_valid";
    private final static String COACH_CLIENT_ID = "coach_client_id";
    private CurrentMembershipValidChecker membershipValidChecker = new CurrentMembershipValidChecker();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        Long clientId;
        if(role.equals(UserRole.COACH)){
            clientId = getClientIdForAppropriateCoach(session,request);
        }
        else{
            clientId = (Long) session.getAttribute(SessionAttributes.ID);
            if(!membershipValidChecker.isCurrentMembershipValid(clientId)){
                request.setAttribute(IS_MEMBERSHIP_VALID, false);
                return new CommandResult(EXERCISE_PAGE,false);
            }else {
                request.setAttribute(IS_MEMBERSHIP_VALID, true);
            }
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

    private Long getClientIdForAppropriateCoach(HttpSession session,HttpServletRequest request){
        Long clientId;
        clientId = (Long) session.getAttribute(COACH_CLIENT_ID);
        session.removeAttribute(COACH_CLIENT_ID);
        if(clientId==null){
            clientId = Long.valueOf(request.getParameter(COACH_CLIENT_ID));
        }
        return clientId;
    }

}