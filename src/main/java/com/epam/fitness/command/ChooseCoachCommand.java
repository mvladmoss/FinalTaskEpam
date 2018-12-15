package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ChooseCoachCommand implements Command {

    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("id");
        Long coachId = Long.valueOf(request.getParameter("coachId"));
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientId);
        if(client.isPresent()){
            clientService.addCoach(client.get(),coachId);
        }
        return new CommandResult(MAIN_PAGE,false);
    }
}
