package com.epam.fitness.command.client;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ChooseCoachCommand implements Command {

    private static final String MAIN_PAGE = "/controller?command=coaches";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("id");
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientId);
        Long coachId = Long.valueOf(request.getParameter("coachId"));
        if(client.isPresent()){
            client.get().setCoachId(coachId);
            clientService.save(client.get());
        }
        return new CommandResult(MAIN_PAGE,false);
    }

}
