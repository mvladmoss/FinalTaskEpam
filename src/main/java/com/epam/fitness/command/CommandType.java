package com.epam.fitness.command;


public enum CommandType{

    ALL_COACH_CLIENTS("all_coach_clients"),
    MAIN("main"),
    LOGIN("login"),
    PROFILE("profile"),
    COACHES("coaches"),
    SIGN_OUT("sign_out"),
    UPDATE_GYM_MEMBERSHIP("update_gym_membership"),
    CHOOSE_COACH("choose_coach"),
    GET_ORDER_PAGE("get_order_page"),
    SHOW_CLIENT_PROGRAM("show_client_program"),
    LANGUAGE("language");

    private String command;
    private CommandType(String command) {
        this.command = command;
    }
}
