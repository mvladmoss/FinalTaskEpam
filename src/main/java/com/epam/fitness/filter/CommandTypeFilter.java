package com.epam.fitness.filter;

import com.epam.fitness.command.access.CommandAccess;
import com.epam.fitness.command.factory.CommandType;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.connection.ConnectionPool;
import com.epam.fitness.utils.page.Page;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CommandTypeFilter implements Filter  {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final String COMMAND = "command";
    private final static String NO_ACCESS_PAGE = "controller?command=no_access";
    private final static String ERROR_PAGE = "/WEB-INF/error/errorPage404.jsp";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        Optional<String> userRole = getUserRoleByRequest(httpServletRequest);
        CommandAccess commandAccess = new CommandAccess();
        List<CommandType> commandTypes = commandAccess.getAvailableCommandTypesByUser(userRole);

        String command = servletRequest.getParameter(COMMAND);
        String commandUpper = command.toUpperCase();
        CommandType commandType;
        try {
             commandType = CommandType.valueOf(commandUpper);
        }catch (IllegalArgumentException exception){
            LOGGER.warn("Incorrect command was input.Command:" + commandUpper);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }

        if (commandTypes.contains(commandType)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(NO_ACCESS_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }

    private Optional<String> getUserRoleByRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Optional<String> roleOptional;
        if (session == null) {
            roleOptional = Optional.empty();
        } else {
            String role = (String)session.getAttribute(SessionAttributes.ROLE);
            roleOptional = Optional.ofNullable(role);
        }
        return roleOptional;
    }

}
