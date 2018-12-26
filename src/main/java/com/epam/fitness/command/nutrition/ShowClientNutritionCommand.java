package com.epam.fitness.command.nutrition;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.service.NutritionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class ShowClientNutritionCommand implements Command {

    private final static String NUTRITION = "nutrition";
    private final static String NUTRITION_PAGE = "/WEB-INF/clientNutrition.jsp";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute("role"));
        long clientId;
        if(role.equals("coach")){
            clientId = Long.valueOf(request.getParameter("client_id"));
        }
        else{
            clientId = (long) session.getAttribute("id");
        }
        NutritionService nutritionService = new NutritionService();
        Optional<Nutrition> nutritionOptional = nutritionService.findByClientId(clientId);
        nutritionOptional.ifPresent(nutrition -> request.setAttribute(NUTRITION,nutrition));
        return new CommandResult(NUTRITION_PAGE,false);
    }
}
