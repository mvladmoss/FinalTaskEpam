package com.epam.fitness.command;


import com.epam.fitness.utils.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainPageCommand implements Command {



    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {

        return new CommandResult(Page.HOME_PAGE.getPage(), false);

    }
}
