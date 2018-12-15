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
                resultCommand = new AllCoachesCommand();
                break;
            }
            case SIGN_OUT: {
                resultCommand = new SingOutCommand();
                break;
            }
            case PROFILE: {
                resultCommand = new ClientProfileCommand();
                break;
            }
            case UPDATE_GYM_MEMBERSHIP: {
                resultCommand = new UpdateGymMembershipCommand();
                break;
            }
            case CHOOSE_COACH:{
                resultCommand = new ChooseCoachCommand();
                break;
            }
            case GET_ORDER_PAGE: {
                resultCommand = new OrderPageCommand();
                break;
            }
            case ALL_COACH_CLIENTS:{
                resultCommand = new CoachClientsCommand();
                break;
            }
            case SHOW_CLIENT_PROGRAM:{
                resultCommand = new ClientProgramCommand();
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid command" + commandEnum);
            }
        }
        return resultCommand;
    }
}
