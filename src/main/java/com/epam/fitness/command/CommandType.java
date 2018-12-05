package com.epam.fitness.command;


public enum CommandType{

    MAIN("main"),
    LOGIN("login"),
    PROFILE("profile"),
    COACHES("coaches"),
    SIGN_OUT("sign_out"),
    BUY_GYM_MEMBERSHIP("buy_gym_membership"),
    LANGUAGE("language");

    private String command;
    private CommandType(String command) {
        this.command = command;
    }
}
