package com.epam.fitness.command.coach;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Coach;
import com.epam.fitness.service.CoachService;
import com.epam.fitness.utils.CurrentMembershipValidChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class AllCoachesCommand implements Command {

    private final static String COACHES = "coaches";
    private static final String COACHES_PAGE = "/WEB-INF/coaches.jsp";
    private static final String ID_OF_CLIENT_COACH = "coach_client_id";
    private final static String IS_MEMBERSHIP_VALID = "is_membership_valid";
    private CurrentMembershipValidChecker membershipValidChecker = new CurrentMembershipValidChecker();



    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("id");
        CoachService coachService = new CoachService();
        List<Coach> coaches = coachService.findAll();
        request.setAttribute(COACHES,coaches);
        checkAndSetIfClientHasCoach(request,clientId);
        if(!membershipValidChecker.isCurrentMembershipValid(clientId)){
            return new CommandResult(COACHES_PAGE,false);
        }else {
            request.setAttribute(IS_MEMBERSHIP_VALID, true);
        }
        return new CommandResult(COACHES_PAGE, false);
    }

    private void checkAndSetIfClientHasCoach(HttpServletRequest request, Long clientId) throws ServiceException {
        CoachService coachService = new CoachService();
        Optional<Coach> coachOptional = coachService.findByClientId(clientId);
        coachOptional.ifPresent(coach -> request.setAttribute(ID_OF_CLIENT_COACH, coach.getId()));
    }


}

