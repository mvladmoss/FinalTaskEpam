package com.epam.fitness.command;

import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.Identifiable;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.CoachService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;



public class LoginCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String ROLE = "role";

    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String ERROR_LOGIN_MESSAGE = "errorLoginMessage";
    private static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication failed!";
    private static final String COMMAND_MAIN = "controller?command=main";
    private static final String NO_LOGIN_PASSWORD_MESSAGE = "You havent input login or password!Try again";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        //check
        if(login == null || password == null){
            request.setAttribute(ERROR_LOGIN_MESSAGE,NO_LOGIN_PASSWORD_MESSAGE);
            return new CommandResult(LOGIN_PAGE, false);
        }
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.login(login, password);

        boolean isClientOrCoachFind = false;
        if (client.isPresent()) {
            setAttributesToSession(client.get(),request);
            isClientOrCoachFind = true;
        } else{
            CoachService coachService = new CoachService();
            Optional<Coach> coach = coachService.login(login,password);
            if(coach.isPresent()){
                setAttributesToSession(coach.get(),request);
                isClientOrCoachFind = true;
            }
        }
        if(isClientOrCoachFind){
            return new CommandResult(COMMAND_MAIN, true);
        }  else {
            request.setAttribute(ERROR_LOGIN_MESSAGE, AUTHENTICATION_FAILED_MESSAGE);
            return new CommandResult(LOGIN_PAGE, false);
        }
    }

    private void setAttributesToSession(Identifiable entity, HttpServletRequest request){
        long clientID = entity.getId();
        HttpSession session = request.getSession();
        session.setAttribute(ID, clientID);
        String entityName = entity.getClass().getSimpleName().toLowerCase();
        session.setAttribute(ROLE,entityName);
        }


}
