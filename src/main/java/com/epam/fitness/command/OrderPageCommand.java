package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.uitls.PeriodCost;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.OrderInformationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class OrderPageCommand implements Command {

    private static final String ORDER_PAGE = "/WEB-INF/buyGymMembership.jsp";
    private static final String TARIFFS = "tariffs";
    private static final String CLIENT_PERSONAL_SALE = "client_personal_sale";
    private static final String CLIENT_CORPORATE_SALE = "client_corporate_sale";
    private static final String IS_MEMBERSHIP_VALID_CURREND_DAY = "end_date_membership";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long clientId = (long) session.getAttribute("id");
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientId);
        OrderInformationService orderInformationService = new OrderInformationService();
        Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientId);
        client.ifPresent(aClient -> {
            Map<String,BigDecimal> tariffs = PeriodCost.getAllCosts();
            request.setAttribute(TARIFFS,tariffs);
            request.setAttribute(CLIENT_PERSONAL_SALE,aClient.getPersonalSale());
            request.setAttribute(CLIENT_CORPORATE_SALE,aClient.getCorporateSale());
            Date today = new Date();
            orderInformation.ifPresent(order -> request.setAttribute(IS_MEMBERSHIP_VALID_CURREND_DAY,order.getTrainEndDate().after(today)));
        });
        return new CommandResult(ORDER_PAGE,false);
    }
}
