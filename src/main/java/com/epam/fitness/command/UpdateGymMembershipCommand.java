package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.uitls.DateProducer;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.uitls.PeriodCost;
import com.epam.fitness.service.OrderInformationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Optional;

public class UpdateGymMembershipCommand implements Command {

    private static final String PROFILE_PAGE = "/controller?command=profile";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long clientID = (long) session.getAttribute("id");
        String periodExtension = request.getParameter("period");
        periodExtension = periodExtension.replace(" ","_");
        BigDecimal cost = new BigDecimal(request.getParameter("cost"));
        OrderInformationService orderInformationService = new OrderInformationService();
        Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientID);
        Date membershipEndDate = new Date();
        if(orderInformation.isPresent()){
            membershipEndDate = orderInformation.get().getTrainEndDate();
        }
        PeriodCost period = PeriodCost.valueOf(periodExtension);
        Date newMembershipEndDate = DateProducer.getNecessaryDate(membershipEndDate,period);
        java.sql.Date newMembershipEndSqlDate = new java.sql.Date(newMembershipEndDate.getTime());
        OrderInformation newOrderInformation = makeOrder(cost,new Timestamp(new Date().getTime()), newMembershipEndSqlDate, clientID);
        orderInformationService.addOrder(newOrderInformation);
        return new CommandResult(PROFILE_PAGE,true);
    }

    private OrderInformation makeOrder(BigDecimal cost, Timestamp paymentData, java.sql.Date endDate, long clientId){
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setClientId(clientId);
        orderInformation.setPaymenData(paymentData);
        orderInformation.setCost(cost);
        orderInformation.setTrainEndDate(endDate);
        return  orderInformation;

    }

}
