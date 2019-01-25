package com.epam.fitness.command.client;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.service.ClientService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.epam.fitness.command.client.constant.ParameterConstants.COACH_PAGE;

/**
 * Designed to reject coach for certain client
 */
public class RejectCoachCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RejectCoachCommand.class.getName());

    /**
     * Process the request, allow {@link Client}  to reject {@link com.epam.fitness.model.Coach} and generates a result of processing in the form of
     * {@link com.epam.fitness.command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link com.epam.fitness.command.CommandResult} object.
     * @throws ServiceException when ServiceException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientId);
        if(client.isPresent()){
            client.get().setCoachId(null);
            clientService.save(client.get());
        }
        LOGGER.info("client with id" + clientId + " refused his coach");
        return new CommandResult(COACH_PAGE,false);
    }
}
