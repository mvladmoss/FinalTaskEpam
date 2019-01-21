package com.epam.fitness.command.client;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.CoachService;
import com.epam.fitness.service.OrderInformationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;



public class ClientProfileCommand implements Command {

    private static final String COACH_NAME = "coach_name";
    private static final String COACH_SURNAME = "coach_surname";
    private static final String CLIENT_PROFILE_PAGE = "/WEB-INF/client/clientProfile.jsp";
    private static final String END_DATE_OF_TRAINS = "end_date_of_trains";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long clientID = (long) session.getAttribute(SessionAttributes.ID);

        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientID);

        if(client.isPresent()) {
            request.setAttribute(UserRole.CLIENT, client.get());
            System.out.println(client.get().getId());
            long coachId = client.get().getCoachId();
            CoachService coachService = new CoachService();
            Optional<Coach> coach = coachService.findById(coachId);
            coach.ifPresent(aCoach -> {
                request.setAttribute(COACH_NAME, aCoach.getName());
                request.setAttribute(COACH_SURNAME, aCoach.getSurname());
            });
            OrderInformationService orderInformationService = new OrderInformationService();
            Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientID);
            orderInformation.ifPresent(aOrder -> request.setAttribute(END_DATE_OF_TRAINS,aOrder.getMembershipEndDate()));
        }
        return new CommandResult(CLIENT_PROFILE_PAGE, false);
    }
    }

