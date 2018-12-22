package com.epam.fitness.command;

import com.epam.fitness.command.exercise.AddExerciseCommand;
import com.epam.fitness.command.client.ClientProfileCommand;
import com.epam.fitness.command.client.ClientProgramCommand;
import com.epam.fitness.command.exercise.DeleteExerciseCommand;
import com.epam.fitness.command.coach.AllCoachesCommand;
import com.epam.fitness.command.coach.ChooseCoachCommand;
import com.epam.fitness.command.coach.CoachClientsCommand;
import com.epam.fitness.command.exercise.UpdateExerciseCommand;

public class CommandFactory {


    public static Command create(String command) {

        command = command.toUpperCase();
        System.out.println(command);
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
            case ADD_EXERCISE:{
                resultCommand = new AddExerciseCommand();
                break;
            }
            case GET_ORDER_PAGE: {
                resultCommand = new OrderPageCommand();
                break;
            }
            case DELETE_EXERCISE:{
                resultCommand = new DeleteExerciseCommand();
                break;
            }
            case UPDATE_EXERCISE:{
                resultCommand = new UpdateExerciseCommand();
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
