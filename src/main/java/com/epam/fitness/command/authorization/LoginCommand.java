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
import java.util.Optional;import com.epam.fitness.exception.ServiceException;

public class LoginCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String ERROR_LOGIN_MESSAGE = "errorLoginMessage";
    private static final String COMMAND_MAIN = "controller?command=main";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        boolean isClientOrCoachFind = false;
        if(!initializeCoachIfExist(login,password,request)){
            isClientOrCoachFind = initializeClientIfExist(login,password,request);
        }else{
            isClientOrCoachFind = true;
        }

        if(isClientOrCoachFind){
            return new CommandResult(COMMAND_MAIN, true);
        }  else {
            request.setAttribute(ERROR_LOGIN_MESSAGE, true);
            return new CommandResult(LOGIN_PAGE, false);
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


}
