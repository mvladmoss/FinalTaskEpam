package com.epam.fitness.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;import com.epam.fitness.exception.ServiceException;



public class SingOutCommand implements Command{

    private static final String ID = "id";
    private static final String LOGIN_PAGE = "/WEB-INF/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(ID);
        return new CommandResult(LOGIN_PAGE, false);
    }

}
