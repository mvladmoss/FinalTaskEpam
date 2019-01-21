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

    private String profilePage = "/controller?command=show_client_nutrition&client_id=";
    private final static String MORNING = "morning";
    private final static String LUNCH = "lunch";
    private final static String DINNER = "dinner";
    private final static String NUTRITION_ID = "nutrition_id";
    private final static String NUTRITION_TIME = "nutrition_time";
    private final static String NUTRITION_DESCRIPTION = "nutrition_description";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long nutritionId = Long.parseLong(request.getParameter(NUTRITION_ID));
        setNewNutrition(request);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            setClientId(nutritionId);
        }
        return new CommandResult(profilePage,true);
    }

    private void setClientId(Long nutritionId) throws ServiceException {

        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByNutritionId(nutritionId);
        clientOptional.ifPresent(client -> profilePage+=client.getId());
    }

    private void setNewNutrition(HttpServletRequest request) throws ServiceException {
        Long nutritionId = Long.parseLong(request.getParameter(NUTRITION_ID));
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
