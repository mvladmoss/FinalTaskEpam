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
import java.util.Date;
import java.sql.Timestamp;
import java.util.Optional;

public class UpdateGymMembershipCommand implements Command {

    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        long clientID = (long) session.getAttribute("id");
        String periodExtension = request.getParameter("period");
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
        orderInformationService.addOrder(cost,new Timestamp(new Date().getTime()), newMembershipEndSqlDate, clientID);
        return new CommandResult(MAIN_PAGE,false);
    }

}
