package com.epam.fitness.command;

import com.epam.fitness.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowGymPhotos implements Command {

    private final static String GYM_PHOTOS = "/WEB-INF/gymPhotos.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(GYM_PHOTOS,false);
    }
}
