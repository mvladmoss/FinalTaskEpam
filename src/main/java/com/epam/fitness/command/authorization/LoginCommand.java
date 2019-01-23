package com.epam.fitness.command.authorization;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.Identifiable;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.CoachService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.utils.RequestParameterValidator;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class LoginCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String AUTHENTICATION_ERROR = "authentication_error";
    private static final String INCORRECT_LOGIN_DATA = "incorrect_login_data";
    private static final String INCORRECT_PASSWORD_DATA = "incorrect_password_data";
    private static final String COMMAND_MAIN = "controller?command=main";
    private final RequestParameterValidator parameterValidator = new RequestParameterValidator();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        if(login==null || !parameterValidator.isLoginValid(login)){
            return forwardToLoginWithError(request,INCORRECT_LOGIN_DATA);
        }
        if(password==null || !parameterValidator.isPasswordValid(password)){
            return forwardToLoginWithError(request,INCORRECT_PASSWORD_DATA);
        }
        boolean isClientOrCoachFind;
        if(!initializeCoachIfExist(login,password,request)){
            isClientOrCoachFind = initializeClientIfExist(login,password,request);
        }else{
            isClientOrCoachFind = true;
        }

        if(isClientOrCoachFind){
            return new CommandResult(COMMAND_MAIN, true);
        }  else {
            return forwardToLoginWithError(request,AUTHENTICATION_ERROR);
        }
    }

    public boolean initializeClientIfExist(String login, String password, HttpServletRequest request) throws ServiceException {
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.login(login, password);
        boolean clientExist = false;
        if(client.isPresent()){
            setAttributesToSession(client.get(),request);
            clientExist = true;
        }
        return clientExist;
    }

    public boolean initializeCoachIfExist(String login, String password, HttpServletRequest request) throws ServiceException {
        CoachService coachService = new CoachService();
        Optional<Coach> coach = coachService.login(login,password);
        boolean coachExist = false;
        if(coach.isPresent()){
            setAttributesToSession(coach.get(),request);
            coachExist = true;
        }
        return coachExist;
    }

    private void setAttributesToSession(Identifiable entity, HttpServletRequest request){
        long userID = entity.getId();
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttributes.ID, userID);
        String entityName = entity.getClass().getSimpleName().toLowerCase();
        session.setAttribute(SessionAttributes.ROLE,entityName);
    }

    private CommandResult forwardToLoginWithError(HttpServletRequest request,String ERROR){
        request.setAttribute(ERROR, true);
        return new CommandResult(LOGIN_PAGE, false);
    }




}
