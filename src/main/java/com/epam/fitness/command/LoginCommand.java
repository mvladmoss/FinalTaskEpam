package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.CoachService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LoginCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String COACH_ID = "coach_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String IS_GYM_MEMBERSHIP = "is_gym_membership";
    private static final String VISITS_NUMBER = "visits_number";
    private static final String SALE = "sale";
    private static final String IS_CORPORATE = "is_corporate";
    private static final String ROLE = "role";

    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    private static final String ERROR_LOGIN_MESSAGE = "errorLoginMessage";
    private static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication failed!";
    private static final String COMMAND_MAIN = "controller?command=main";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        //Make check to null if i don't make login or password
        ClientService clientService = new ClientService();
        Optional<Client> client = clientService.login(login, password);

        boolean isClientOrCoach = false;
        if (client.isPresent()) {
            setClientAttributesToSession(client.get(),request);
            isClientOrCoach = true;
        } else{
            CoachService coachService = new CoachService();
            Optional<Coach> coach = coachService.login(login,password);
            if(coach.isPresent()){
                setCoachAttributesToSession(coach.get(),request);
                isClientOrCoach = true;
            }
        }
        if(isClientOrCoach){
            return new CommandResult(COMMAND_MAIN, true);
        }  else {
            request.setAttribute(ERROR_LOGIN_MESSAGE, AUTHENTICATION_FAILED_MESSAGE);
            return new CommandResult(LOGIN_PAGE, false);
        }
    }

    private void setClientAttributesToSession(Client client, HttpServletRequest request){
        long clientID = client.getId();
        String name = client.getName();
        String surname = client.getSurname();
        HttpSession session = request.getSession();

        session.setAttribute(ID, clientID);
        session.setAttribute(NAME,name);
        session.setAttribute(SURNAME,surname);
        session.setAttribute(ROLE,"client");
        }

    private void setCoachAttributesToSession(Coach coach,HttpServletRequest request){
        long coachID = coach.getId();
        String name = coach.getName();
        String surname = coach.getSurname();
        HttpSession session = request.getSession();

        session.setAttribute(ID, coachID);
        session.setAttribute(NAME,name);
        session.setAttribute(SURNAME,surname);
        session.setAttribute(ROLE,"coach");
    }

}
