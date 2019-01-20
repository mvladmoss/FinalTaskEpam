package com.epam.fitness.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainPageCommand implements Command {

    private static final String MAIN_PAGE = "/WEB-INF/mainPage.jsp";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {

        return new CommandResult(MAIN_PAGE, false);

    }
}
