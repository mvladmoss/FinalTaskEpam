package com.epam.fitness.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LanguageFilter implements Filter {

    private static final String RU = "RU";
    private static final String EN = "EN";
    private static final String LANGUAGE = "language";
    private static final String NEXT_LANGUAGE = "nextLanguage";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);
        if (language == null) {
            language = EN;
            session.setAttribute(LANGUAGE, language);
            session.setAttribute(NEXT_LANGUAGE, RU);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
