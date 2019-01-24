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
import com.epam.fitness.utils.RequestParameterValidator;


public class UpdateClientNutritionCommand implements Command {

    private static final String PROFILE_PAGE = "/controller?command=show_client_nutrition";
    private final static String MORNING = "morning";
    private final static String LUNCH = "lunch";
    private final static String DINNER = "dinner";
    private final static String NUTRITION_ID = "nutrition_id";
    private final static String NUTRITION_TIME = "nutrition_time";
    private final static String NUTRITION_DESCRIPTION = "nutrition_description";
    private final static String COACH_CLIENT_ID = "coach_client_id";
    private final static String COACH_CLIENT_PAGE = "/controller?command=all_coach_clients";
    private final static String CLIENT_EXERCISE_PAGE = "/controller?command=show_client_nutrition";
    private final static String INCORRECT_INPUT_NUTRITION_DATA_ERROR = "incorrect_input_nutrition_data_error";
    private final RequestParameterValidator parameterValidator = new RequestParameterValidator();


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String newNutritionDescription = request.getParameter(NUTRITION_DESCRIPTION);
        if(newNutritionDescription==null || !parameterValidator.isNutritionDescriptionValid(newNutritionDescription)){
            return forwardToNutritionPageWithError(request);
        }
        Long nutritionId = Long.parseLong(request.getParameter(NUTRITION_ID));
        String nutritionTime = request.getParameter(NUTRITION_TIME);
        setNewNutrition(nutritionId,nutritionTime,newNutritionDescription);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            setClientIdForCoach(nutritionId,request);
        }
        return new CommandResult(PROFILE_PAGE,true);
    }

    private void setClientIdForCoach(Long nutritionId,HttpServletRequest request) throws ServiceException {

        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByNutritionId(nutritionId);
        clientOptional.ifPresent(client -> {
            HttpSession session = request.getSession();
            session.setAttribute(COACH_CLIENT_ID,client.getId());
        });
    }

    private void setNewNutrition(Long nutritionId,String nutritionTime, String newNutritionDescription) throws ServiceException {
        NutritionService nutritionService = new NutritionService();
        Optional<Nutrition> nutritionOptional = nutritionService.findById(nutritionId);
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

    private CommandResult forwardToNutritionPageWithError(HttpServletRequest request) {
        request.setAttribute(INCORRECT_INPUT_NUTRITION_DATA_ERROR, true);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            return new CommandResult(COACH_CLIENT_PAGE,false);
        }else{
            return new CommandResult(CLIENT_EXERCISE_PAGE,false);
        }
    }
}
