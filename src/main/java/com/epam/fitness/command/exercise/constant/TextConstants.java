package com.epam.fitness.command.exercise.constant;

import com.epam.fitness.command.exercise.UpdateExerciseCommand;
import org.apache.log4j.Logger;

public class TextConstants {
    public final static String PROFILE_PAGE = "/controller?command=show_client_exercises";
    public final static String COACH_CLIENT_PAGE = "/controller?command=all_coach_clients";
    public final static String CLIENT_EXERCISE_PAGE = "/controller?command=show_client_exercises";
    public final static String INCORRECT_INPUT_DATA_ERROR = "incorrect_input_data_error";
    public static final String REPEATS = "repeats";
    public static final String SET_NUMBER = "set_number";
    public static final String TRAIN_DAY = "trainDay";
    public static final String PROGRAM_ID = "programId";
    public static final String EXERCISE_ID = "exerciseId";
    public final static String COACH_CLIENT_ID = "coach_client_id";
    public final static String EXERCISE_DTO_ID = "exerciseDtoId";
    public final static String INVALID_EXERCISE_ID_FORMAT = "invalid_exercise_id_format";
    public final static String NOT_EXIST_EXERCISE_ID = "not_exist_exercise_id";
    public final static String PROGRAM = "program";
    public final static String EXERCISE_PAGE = "/WEB-INF/client/clientExercise.jsp";
    public final static String IS_MEMBERSHIP_VALID = "is_membership_valid";
    public final static String EXERCISES = "exercises";
    public static final Logger LOGGER = Logger.getLogger(UpdateExerciseCommand.class.getName());
}
