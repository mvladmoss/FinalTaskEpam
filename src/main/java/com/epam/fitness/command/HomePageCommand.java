package com.epam.fitness.command;


import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.utils.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Designed to provide home page
 */
public class HomePageCommand implements Command {

    /**
     * Process the request, provide home page and generates a result of processing in the form of
     * {@link com.epam.fitness.command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link com.epam.fitness.command.CommandResult} object.
     * @throws ServiceException when ServiceException is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {

        return new CommandResult(Page.HOME_PAGE.getPage(), false);

    }
}
