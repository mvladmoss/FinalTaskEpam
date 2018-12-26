package com.epam.fitness.command.coach;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Coach;
import com.epam.fitness.service.CoachService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class AllCoachesCommand implements Command {

    private final static String COACHES = "coaches";
    private static final String COMMAND_COACHES = "/WEB-INF/coaches.jsp";
    private static final String ID_OF_CLIENT_COACH = "coach_client_id";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        CoachService coachService = new CoachService();
        List<Coach> coaches = coachService.findAll();
        request.setAttribute(COACHES,coaches);
        checkAndSetIfClientHasCoach(request);
        return new CommandResult(COMMAND_COACHES, false);
    }

    private void checkAndSetIfClientHasCoach(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("id");
        CoachService coachService = new CoachService();
        Optional<Coach> coachOptional = coachService.findByClientId(clientId);
        coachOptional.ifPresent(coach -> request.setAttribute(ID_OF_CLIENT_COACH, coach.getId()));
    }
    }

