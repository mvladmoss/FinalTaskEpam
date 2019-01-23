package com.epam.fitness.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParameterValidator {

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][\\w-_.]{1,20}$");
    private static final Pattern NAME_SURNAME_PATTERN = Pattern.compile("^[a-zA-Z][\\w-_.]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[\\w-_.]{3,20}$");

    public boolean isLoginValid(String login){
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        return matcher.matches();
    }

    public boolean isNameValid(String name){
        Matcher matcher = NAME_SURNAME_PATTERN.matcher(name);
        return matcher.matches();
    }

    public boolean isSurnameValid(String surname){
        Matcher matcher = NAME_SURNAME_PATTERN.matcher(surname);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password){
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }


}
