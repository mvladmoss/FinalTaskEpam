package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.CoachService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class ProfileCommand implements Command {

    private static final String ID = "id";
    private static final String USER = "user";
    private static final String COACH_NAME = "coach_name";
    private static final String COACH_SURNAME = "coach_surname";
    private static final String CLIENT_PROFILE_PAGE = "/WEB-INF/clientProfile.jsp";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute(ID);

        ClientService clientService = new ClientService();
        Optional<Client> user = clientService.findById(id);
        user.ifPresent(aUser -> request.setAttribute(USER, aUser));
        //ASK
        if(user.isPresent()) {
            int coachId = user.get().getCoachId();
            CoachService coachService = new CoachService();
            Optional<Coach> coach = coachService.findById(coachId);
            coach.ifPresent(aCoach -> {
                request.setAttribute(COACH_NAME,aCoach.getName());
                request.setAttribute(COACH_SURNAME,aCoach.getSurname());
            }
            );
        }
        return new CommandResult(CLIENT_PROFILE_PAGE, false);
    }
    }

