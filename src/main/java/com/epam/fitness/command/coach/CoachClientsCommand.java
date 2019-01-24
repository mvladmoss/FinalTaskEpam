package com.epam.fitness.command.coach;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.service.ClientService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.epam.fitness.exception.ServiceException;
import static com.epam.fitness.command.coach.constant.ParameterConstants.*;


public class CoachClientsCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long id = (long) session.getAttribute(SessionAttributes.ID);
        ClientService clientService = new ClientService();
        List<Client> allClients = clientService.findByCoachId(id);
        session.setAttribute(ALL_CLIENTS, allClients);
        return new CommandResult(COACH_CLIENTS_PAGE,false);
    }
}
