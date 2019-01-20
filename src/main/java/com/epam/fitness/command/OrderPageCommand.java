package com.epam.fitness.command;

import com.epam.fitness.model.Client;
import com.epam.fitness.utils.CurrentMembershipValidChecker;
import com.epam.fitness.utils.PeriodCost;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.OrderInformationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class OrderPageCommand implements Command {

    private static final String ORDER_PAGE = "/WEB-INF/orderPages.jsp";
    private static final String TARIFFS = "tariffs";
    private static final String CLIENT_PERSONAL_SALE = "client_personal_discount";
    private static final String CLIENT_CORPORATE_SALE = "client_corporate_discount";
    private final static String IS_MEMBERSHIP_VALID = "is_membership_valid";
    private CurrentMembershipValidChecker membershipValidChecker = new CurrentMembershipValidChecker();


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long clientId = (long) session.getAttribute("id");
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findById(clientId);
        if(client.isPresent()){
            request.setAttribute(CLIENT_PERSONAL_SALE,client.get().getPersonalDiscount());
            request.setAttribute(CLIENT_CORPORATE_SALE,client.get().getCorporateDiscount());
            OrderInformationService orderInformationService = new OrderInformationService();
            Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientId);
            if(orderInformation.isPresent()){
                request.setAttribute(IS_MEMBERSHIP_VALID,membershipValidChecker.isCurrentMembershipValid(client.get().getId()));
            }
        }
        return new CommandResult(ORDER_PAGE,false);
    }
}
