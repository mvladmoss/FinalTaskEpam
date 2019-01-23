package com.epam.fitness.command.nutrition;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.service.NutritionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.utils.CurrentMembershipValidChecker;


public class ShowClientNutritionCommand implements Command {

    private final static String NUTRITION = "nutrition";
    private final static String NUTRITION_PAGE = "/WEB-INF/client/clientNutrition.jsp";
    private final static String IS_MEMBERSHIP_VALID = "is_membership_valid";
    private final static String COACH_CLIENT_ID = "coach_client_id";
    private final static Integer MAX_NUMBER_SYMBOLS_VALUE = 300;
    private final static String MAX_NUMBER_SYMBOLS_ATTRIBUTE = "max_number_symbols_attribute";
    private CurrentMembershipValidChecker membershipValidChecker = new CurrentMembershipValidChecker();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE,MAX_NUMBER_SYMBOLS_VALUE);
        Long clientId;
        if(role.equals(UserRole.COACH)){
            clientId = getClientIdForAppropriateCoach(session,request);
        }
        else{
            clientId = (Long) session.getAttribute(SessionAttributes.ID);
            if(!membershipValidChecker.isCurrentMembershipValid(clientId)){
                request.setAttribute(IS_MEMBERSHIP_VALID, false);
                return new CommandResult(NUTRITION_PAGE,false);
            }else {
                request.setAttribute(IS_MEMBERSHIP_VALID, true);
            }
        }

        NutritionService nutritionService = new NutritionService();
        Optional<Nutrition> nutritionOptional = nutritionService.findByClientId(clientId);
        nutritionOptional.ifPresent(nutrition -> request.setAttribute(NUTRITION,nutrition));
        return new CommandResult(NUTRITION_PAGE,false);
    }

    private Long getClientIdForAppropriateCoach(HttpSession session,HttpServletRequest request){
        Long clientId;
        clientId = (Long) session.getAttribute(COACH_CLIENT_ID);
        session.removeAttribute(COACH_CLIENT_ID);
        if(clientId==null){
            clientId = Long.valueOf(request.getParameter(COACH_CLIENT_ID));
        }
        return clientId;
    }
}
