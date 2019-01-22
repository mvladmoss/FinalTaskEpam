package com.epam.fitness.command.nutrition;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.NutritionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class UpdateClientNutritionCommand implements Command {

    private static final String PROFILE_PAGE = "/controller?command=show_client_nutrition";
    private final static String MORNING = "morning";
    private final static String LUNCH = "lunch";
    private final static String DINNER = "dinner";
    private final static String NUTRITION_ID = "nutrition_id";
    private final static String NUTRITION_TIME = "nutrition_time";
    private final static String NUTRITION_DESCRIPTION = "nutrition_description";
    private final static String COACH_CLIENT_ID = "coach_client_id";



    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long nutritionId = Long.parseLong(request.getParameter(NUTRITION_ID));
        setNewNutrition(nutritionId,request);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            setClientId(nutritionId,request);
        }
        return new CommandResult(PROFILE_PAGE,true);
    }

    private void setClientId(Long nutritionId,HttpServletRequest request) throws ServiceException {

        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByNutritionId(nutritionId);
        clientOptional.ifPresent(client -> {
            HttpSession session = request.getSession();
            session.setAttribute(COACH_CLIENT_ID,client.getId());
        });
    }

    private void setNewNutrition(Long nutritionId,HttpServletRequest request) throws ServiceException {
        String nutritionTime = request.getParameter(NUTRITION_TIME);
        NutritionService nutritionService = new NutritionService();
        Optional<Nutrition> nutritionOptional = nutritionService.findById(nutritionId);
        String newNutritionDescription = request.getParameter(NUTRITION_DESCRIPTION);
        switch (nutritionTime){
            case MORNING : {
                nutritionOptional.ifPresent(nutrition -> nutrition.setMorningNutrition(newNutritionDescription));
                break;
            }
            case LUNCH : {
                nutritionOptional.ifPresent(nutrition -> nutrition.setLunchNutrition(newNutritionDescription));
                break;
            }
            case DINNER : {
                nutritionOptional.ifPresent(nutrition -> nutrition.setDinnerNutrition(newNutritionDescription));
                break;
            }
        }
        if(nutritionOptional.isPresent()){
            nutritionService.save(nutritionOptional.get());
        }

    }
}
