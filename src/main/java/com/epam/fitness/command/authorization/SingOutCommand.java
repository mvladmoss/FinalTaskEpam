package com.epam.fitness.command.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.controller.Controller;
import com.epam.fitness.utils.page.Page;
import org.apache.log4j.Logger;


public class SingOutCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SingOutCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttributes.ID);
        String userRole = (String) session.getAttribute(SessionAttributes.ROLE);
        LOGGER.info("user was above: id:" + userId + " role:" + userRole);
        session.removeAttribute(SessionAttributes.ID);
        session.removeAttribute(SessionAttributes.ROLE);
        return new CommandResult(Page.LOGIN.getPage(), false);
    }

}
