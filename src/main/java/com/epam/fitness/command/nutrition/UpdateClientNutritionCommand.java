package com.epam.fitness.command.nutrition;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.NutritionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;


public class UpdateClientNutritionCommand implements Command {

    private String profilePage = "/controller?command=show_client_nutrition&client_id=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String nutritionText = request.getParameter("nutrition_description");
        Long nutritionId = Long.valueOf(request.getParameter("nutrition_id"));
        NutritionService nutritionService = new NutritionService();
        Optional<Nutrition> nutrition = nutritionService.findById(nutritionId);
        if(nutrition.isPresent()){
            nutrition.get().setDescription(nutritionText);
            nutritionService.save(nutrition.get());
        }
        HttpSession session = request.getSession();
        if(session.getAttribute("role").equals("coach")){
            setCLientId(request);
        }
        return new CommandResult(profilePage,true);
    }

    private void setCLientId(HttpServletRequest request) throws ServiceException {
        long nutritionId = Long.parseLong(request.getParameter("nutrition_id"));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByNutritionId(nutritionId);
        clientOptional.ifPresent(client -> profilePage+=client.getId());
    }
}
