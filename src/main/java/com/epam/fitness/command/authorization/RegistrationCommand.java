package com.epam.fitness.command.authorization;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.model.Program;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.utils.sale.SaleSystem;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.NutritionService;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {

    private static final String COMMAND_MAIN = "controller?command=main";
    private final static Integer START_VISIT_NUMBER = 0;
    private final static Float CORPORATE_SALE = 0.0f;
    private final static Integer TRAINS_PER_WEEK = 3;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Client client = buildClient(request);
        ClientService clientService = new ClientService();
        Long clientId = clientService.save(client);
        client.setID(clientId);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttributes.ID,client.getId());
        session.setAttribute(SessionAttributes.ROLE, UserRole.CLIENT);
        return new CommandResult(COMMAND_MAIN,true);
    }

    private Client buildClient(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter("loginRegister");
        String password = request.getParameter("passwordRegister");
        String name = request.getParameter("nameRegister");
        String surname = request.getParameter("surnameRegister");
        Client client = new Client();
        client.setLogin(login);
        client.setPassword(password);
        client.setName(name);
        client.setSurname(surname);
        client.setMembershipPurchasedNumber(START_VISIT_NUMBER);
        float personalSale = SaleSystem.getSaleByVisitNumber(START_VISIT_NUMBER);
        client.setPersonalSale(personalSale);
        client.setCorporateSale(CORPORATE_SALE);
        Program program = createProgram();
        client.setProgramId(program.getId());
        return client;
    }

    private Program createProgram() throws ServiceException {
        Program program = new Program();
        Nutrition nutrition = createNutrition();
        program.setNutrition(nutrition);
        program.setTrainsPerWeek(TRAINS_PER_WEEK);
        ProgramService  programService = new ProgramService();
        Long programId = programService.save(program);
        program.setId(programId);
        return program;
    }

    private Nutrition createNutrition() throws ServiceException {
        Nutrition nutrition = new Nutrition();
        NutritionService nutritionService = new NutritionService();
        Long nutritionId  = nutritionService.save(nutrition);
        nutrition.setId(nutritionId);
        return nutrition;
    }
}
