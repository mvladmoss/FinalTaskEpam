package com.epam.fitness.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParameterValidator {

    private static final Pattern LOGIN_PATTERN = Pattern.compile("^[a-zA-Z][\\w-_.]{1,20}$");
    private static final Pattern NAME_SURNAME_PATTERN = Pattern.compile("^[a-zA-Z]{2,20}");
    private static final Pattern SET_NUMBER_REPEATS = Pattern.compile("^[1-9]{1,2}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[\\w-_.]{3,20}$");
    private final static Pattern CARD_NUMBER_PATTERN = Pattern.compile("^[1-9]{16}");
    private final static Pattern INPUT_TEXT_PATTERN = Pattern.compile("[\\w().,]{1,300}");

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

    public boolean isSetNumberValid(String setNumber){
        Matcher matcher = SET_NUMBER_REPEATS.matcher(setNumber);
        return matcher.matches();
    }

    public boolean isRepeatsValid(String repeats){
        Matcher matcher = SET_NUMBER_REPEATS.matcher(repeats);
        return matcher.matches();
    }

    public boolean isCardNumberValid(String cardNumber){
        Matcher matcher = CARD_NUMBER_PATTERN.matcher(cardNumber);
        return matcher.matches();
    }

    public boolean isNutritionDescriptionValid(String description){
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(description);
        return matcher.matches();
    }

    public boolean isCommentContentValid(String commentContent){
        Matcher matcher = INPUT_TEXT_PATTERN.matcher(commentContent);
        return matcher.matches();
    }


}
