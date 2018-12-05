package com.epam.fitness.command;

public class CommandFactory {


    public static Command create(String command) {

        command = command.toUpperCase();
        CommandType commandEnum = CommandType.valueOf(command);

        Command resultCommand;
        switch (commandEnum) {
            case LOGIN: {
                resultCommand = new LoginCommand();
                break;
            }
            case LANGUAGE: {
                resultCommand = new LanguageCommand();
                break;
            }
            case MAIN: {
                resultCommand = new MainCommand();
                break;
            }
            case COACHES: {
                resultCommand = new CoachCommand();
                break;
            }
            case SIGN_OUT: {
                resultCommand = new SingOutCommand();
                break;
            }
            case PROFILE: {
                resultCommand = new ProfileCommand();
                break;
            }
            case BUY_GYM_MEMBERSHIP: {
                resultCommand = new BuyGymMembershipCommand();
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid command" + commandEnum);
            }
        }
        return resultCommand;
    }
}
