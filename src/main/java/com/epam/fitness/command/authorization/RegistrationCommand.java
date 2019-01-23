package com.epam.fitness.command.authorization;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.*;
import com.epam.fitness.service.CoachService;
import com.epam.fitness.utils.RequestParameterValidator;
import com.epam.fitness.utils.page.Page;
import com.epam.fitness.utils.sale.SaleSystem;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.NutritionService;
import com.epam.fitness.service.ProgramService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegistrationCommand implements Command {

    private static final String COMMAND_MAIN = "controller?command=main";
    private final static Integer START_VISIT_NUMBER = 0;
    private final static Float CORPORATE_SALE = 0.0f;
    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private final static Integer STANDARD_TRAINS_PER_WEEK = 3;
    private static final String INCORRECT_LOGIN_DATA = "incorrect_login_data";
    private static final String INCORRECT_PASSWORD_DATA = "incorrect_password_data";
    private static final String INCORRECT_NAME_SURNAME_DATA = "incorrect_name_surname_data";
    private final static String LOGIN_EXIST_ERROR = "login_exist_error";
    private final static String LOGIN_FOR_REGISTER = "login_register";
    private final static String PASSWORD_FOR_REGISTER = "password_register";
    private final static String NAME_FOR_REGISTER = "name_register";
    private final static String SURNAME_FOR_REGISTER = "surname_register";
    private final RequestParameterValidator parameterValidator = new RequestParameterValidator();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN_FOR_REGISTER);
        if(login==null || !parameterValidator.isLoginValid(login)){
            return forwardToLoginWithError(request,INCORRECT_LOGIN_DATA);
        }
        String password = request.getParameter(PASSWORD_FOR_REGISTER);
        if(password==null || !parameterValidator.isPasswordValid(password)){
            return forwardToLoginWithError(request,INCORRECT_PASSWORD_DATA);
        }
        String name = request.getParameter(NAME_FOR_REGISTER);
        String surname = request.getParameter(SURNAME_FOR_REGISTER);
        if(name==null || surname==null || !(parameterValidator.isNameValid(name) && parameterValidator.isSurnameValid(surname))){
            return forwardToLoginWithError(request,INCORRECT_NAME_SURNAME_DATA);
        }
        if(isLoginExistInDatabase(login)){
            request.setAttribute(LOGIN_EXIST_ERROR,true);
            return new CommandResult(Page.LOGIN.getPage(),false);
        }
        Client client = buildClient(request);
        ClientService clientService = new ClientService();
        Long clientId = clientService.save(client);
        client.setID(clientId);
        setSessionAttributes(request,clientId);
        return new CommandResult(COMMAND_MAIN,true);
    }

    private Client buildClient(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter(LOGIN_FOR_REGISTER);
        String password = request.getParameter(PASSWORD_FOR_REGISTER);
        String name = request.getParameter(NAME_FOR_REGISTER);
        String surname = request.getParameter(SURNAME_FOR_REGISTER);
        float personalSale = SaleSystem.getSaleByVisitNumber(START_VISIT_NUMBER);
        Program program = buildProgram();
        return new Client(null,null,name,surname,login,password,START_VISIT_NUMBER,personalSale,CORPORATE_SALE,program.getId());
    }

    private Program buildProgram() throws ServiceException {
        Program program = new Program();
        Nutrition nutrition = buildNutrition();
        program.setNutritionId(nutrition.getId());
        program.setTrainsPerWeek(STANDARD_TRAINS_PER_WEEK);
        ProgramService  programService = new ProgramService();
        Long programId = programService.save(program);
        program.setId(programId);
        return program;
    }

    private Nutrition buildNutrition() throws ServiceException {
        Nutrition nutrition = new Nutrition();
        NutritionService nutritionService = new NutritionService();
        Long nutritionId  = nutritionService.save(nutrition);
        nutrition.setId(nutritionId);
        return nutrition;
    }

    public boolean isLoginExistInDatabase(String login) throws ServiceException {
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.findByLogin(login);
        if(client.isPresent()){
            return true;
        }
        CoachService coachService = new CoachService();
        Optional<Coach> coach = coachService.findByLogin(login);
        return coach.isPresent();
    }

    private void setSessionAttributes(HttpServletRequest request,Long clientId){
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttributes.ID,clientId);
        session.setAttribute(SessionAttributes.ROLE, UserRole.CLIENT);
    }

    private CommandResult forwardToLoginWithError(HttpServletRequest request,String ERROR){
        request.setAttribute(ERROR, true);
        return new CommandResult(LOGIN_PAGE, false);
    }

}