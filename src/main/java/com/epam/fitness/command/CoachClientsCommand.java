package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CoachClientsCommand implements Command {

    private final static String ALL_CLIENTS = "all_clients";
    private final static String COACH_CLIENTS_PAGE = "/WEB-INF/coachClients.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute("id");
        ClientService clientService = new ClientService();
        List<Client> allClients = clientService.findByCoachId(id);
        session.setAttribute(ALL_CLIENTS, allClients);
        return new CommandResult(COACH_CLIENTS_PAGE,false);
    }
}
