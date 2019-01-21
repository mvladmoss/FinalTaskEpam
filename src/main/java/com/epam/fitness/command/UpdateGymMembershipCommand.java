package com.epam.fitness.command;

import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
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
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class UpdateGymMembershipCommand implements Command {

    private static final String PROFILE_PAGE = "/controller?command=profile";
    private static final String COST = "cost";
    private static final String PERIOD = "period";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long clientID = (long) session.getAttribute(SessionAttributes.ID);
        increaseClientVisitNumber(clientID);
        BigDecimal cost = new BigDecimal(request.getParameter(COST));
        java.sql.Date newEndMembershipDate = defineNewEndMembershipEndDate(request,clientID);
        OrderInformation newOrderInformation = makeOrder(cost,new Timestamp(new Date().getTime()), newEndMembershipDate, clientID);
        OrderInformationService orderInformationService = new OrderInformationService();
        orderInformationService.save(newOrderInformation);
        return new CommandResult(PROFILE_PAGE,true);
    }

    private OrderInformation makeOrder(BigDecimal cost, Timestamp paymentData, java.sql.Date endDate, long clientId){
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setClientId(clientId);
        orderInformation.setPaymentData(paymentData);
        orderInformation.setCost(cost);
        orderInformation.setMembershipEndDate(endDate);
        return  orderInformation;
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



}
