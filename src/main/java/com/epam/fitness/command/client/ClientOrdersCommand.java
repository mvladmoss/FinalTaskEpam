package com.epam.fitness.command.client;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.exception.IncorrectInputDataException;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.service.OrderInformationService;
import com.epam.fitness.utils.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.fitness.command.client.constant.ParameterConstants.ORDERS;

/**
 * Designed for searching client orders
 */
public class ClientOrdersCommand implements Command {

    /**
     * Process the request, find {@link OrderInformation} orders and generates a result of processing in the form of
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
        OrderInformationService orderInformationService = new OrderInformationService();
        List<OrderInformation> list = orderInformationService.findByOrdersClientId(clientId);
        request.setAttribute(ORDERS,list);
        return new CommandResult(Page.CLIENTS_ORDERS_PAGE.getPage(),false);
    }
}
