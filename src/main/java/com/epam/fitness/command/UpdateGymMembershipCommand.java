package com.epam.fitness.command;

import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.utils.RequestParameterValidator;
import com.epam.fitness.utils.sale.SaleSystem;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.utils.DateProducer;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.utils.PeriodCost;
import com.epam.fitness.service.OrderInformationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.fitness.exception.ServiceException;


public class UpdateGymMembershipCommand implements Command {

    private static final String PROFILE_PAGE = "/controller?command=profile";
    private static final String COST = "cost";
    private static final String PERIOD = "period";
    private static final String CARD_NUMBER = "cardNumber";
    private static final String PAYMENT_ERROR = "payment_error";
    private static final String ORDER_PAGE = "/controller?command=get_order_page";
    private final RequestParameterValidator parameterValidator = new RequestParameterValidator();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String cardNumber = request.getParameter(CARD_NUMBER);
        if(cardNumber==null || !parameterValidator.isCardNumberValid(cardNumber)){
            return forwardToLoginWithError(request,PAYMENT_ERROR);
        }
        HttpSession session = request.getSession();
        Long clientID = (Long) session.getAttribute(SessionAttributes.ID);
        increaseClientVisitNumber(clientID);
        BigDecimal cost = new BigDecimal(request.getParameter(COST));
        java.sql.Date newEndMembershipDate = defineNewEndMembershipEndDate(request,clientID);
        OrderInformation newOrderInformation = new OrderInformation(null,cost,new Timestamp(new Date().getTime()), newEndMembershipDate, clientID,cardNumber);
        OrderInformationService orderInformationService = new OrderInformationService();
        orderInformationService.save(newOrderInformation);
        return new CommandResult(PROFILE_PAGE,true);
    }

    private java.sql.Date defineNewEndMembershipEndDate(HttpServletRequest request, Long clientID) throws ServiceException {
        String periodExtension = request.getParameter(PERIOD);
        periodExtension = periodExtension.replace(" ","_");
        OrderInformationService orderInformationService = new OrderInformationService();
        Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientID);
        Date membershipEndDate = new Date();
        if(orderInformation.isPresent()){
            membershipEndDate = orderInformation.get().getMembershipEndDate();
        }
        PeriodCost period = PeriodCost.valueOf(periodExtension);
        Date newMembershipEndDate = DateProducer.getNecessaryDate(membershipEndDate,period);
        return new java.sql.Date(newMembershipEndDate.getTime());
    }

    private void increaseClientVisitNumber(long clientId) throws ServiceException {
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findById(clientId);
        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            int currentVisitNumber = client.getMembershipPurchasedNumber();
            Float newPersonalDiscount = SaleSystem.getSaleByVisitNumber(currentVisitNumber);
            client.setMembershipPurchasedNumber(++currentVisitNumber);
            client.setPersonalSale(newPersonalDiscount);
            clientService.save(client);
        }

    }

    private CommandResult forwardToLoginWithError(HttpServletRequest request,String error) {
        request.setAttribute(error, true);
        return new CommandResult(ORDER_PAGE,false);
    }

}
