package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
import com.epam.fitness.exception.IncorrectInputDataException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.model.dto.ExerciseDto;
import com.epam.fitness.service.ClientService;
import com.epam.fitness.service.ExerciseDtoService;
import com.epam.fitness.service.ExerciseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.UserRole;
import com.epam.fitness.utils.RequestParameterValidator;


public class AddExerciseCommand implements Command {

    private final static String PROFILE_PAGE = "/controller?command=show_client_exercises";
    private final static String COACH_CLIENT_PAGE = "/controller?command=all_coach_clients";
    private final static String CLIENT_EXERCISE_PAGE = "/controller?command=show_client_exercises";
    private final static String INCORRECT_INPUT_DATA_ERROR = "incorrect_input_data_error";
    private static final String REPEATS = "repeats";
    private static final String SET_NUMBER = "set_number";
    private static final String TRAIN_DAY = "trainDay";
    private static final String PROGRAM_ID = "programId";
    private static final String EXERCISE_ID = "exerciseId";
    private final static String COACH_CLIENT_ID = "coach_client_id";
    private final RequestParameterValidator parameterValidator = new RequestParameterValidator();


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectInputDataException {
        HttpSession session = request.getSession();
        String repeatsString = request.getParameter(REPEATS);
        String setNumberString = request.getParameter(SET_NUMBER);
        if(repeatsString==null || !parameterValidator.isRepeatsValid(repeatsString)){
            return forwardToLoginWithError(request);
        }
        if(setNumberString==null || !parameterValidator.isSetNumberValid(setNumberString)){
            return forwardToLoginWithError(request);
        }
        Integer repeats = Integer.valueOf(repeatsString);
        Integer setNumber = Integer.valueOf(setNumberString);
        ExerciseDto exerciseDto = makeExercise(request,repeats,setNumber);
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        exerciseDtoService.save(exerciseDto);
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            setClientIdForCoach(request);
        }
        return new CommandResult(PROFILE_PAGE,true);
    }

    private ExerciseDto makeExercise(HttpServletRequest request, Integer repeats, Integer setNumber) throws ServiceException, IncorrectInputDataException {
        ExerciseService service = new ExerciseService();
        Long exerciseId = Long.parseLong(request.getParameter(EXERCISE_ID));
        Optional<Exercise> exercise = service.findById(exerciseId);
        Integer trainDay = Integer.parseInt(request.getParameter(TRAIN_DAY));
        Long programId = Long.parseLong(request.getParameter(PROGRAM_ID));
        return new ExerciseDto(null, exercise.get(), programId, repeats, setNumber, trainDay);
    }

    private void setClientIdForCoach(HttpServletRequest request) throws ServiceException {
        Long programId = Long.parseLong(request.getParameter(PROGRAM_ID));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByProgramId(programId);
        clientOptional.ifPresent(client -> {
            HttpSession session = request.getSession();
            session.setAttribute(COACH_CLIENT_ID,client.getId());
        });
    }

    private CommandResult forwardToLoginWithError(HttpServletRequest request) {
        request.setAttribute(INCORRECT_INPUT_DATA_ERROR, true);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            return new CommandResult(COACH_CLIENT_PAGE,false);
        }else{
            return new CommandResult(CLIENT_EXERCISE_PAGE,false);
        }
    }
}
