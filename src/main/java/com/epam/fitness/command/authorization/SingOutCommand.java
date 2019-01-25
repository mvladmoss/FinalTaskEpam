package com.epam.fitness.command.authorization;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.utils.page.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Designed to perform sign out process.
 */
public class SingOutCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SingOutCommand.class.getName());

    /**
     * Process the request, sing out from profile and generates a result of processing in the form of
     * {@link com.epam.fitness.command.CommandResult} object.
     *
     * @param request  an {@link HttpServletRequest} object that contains client request
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @return A response in the form of {@link com.epam.fitness.command.CommandResult} object.
     */

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
