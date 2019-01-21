package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
import com.epam.fitness.command.session.SessionAttributes;
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


public class AddExerciseCommand implements Command {

    private static  String profilePage = "/controller?command=show_client_exercises&client_id=";
    private static final String REPEATS = "repeats";
    private static final String SET_NUMBER = "setNumber";
    private static final String TRAIN_DAY = "trainDay";
    private static final String PROGRAM_ID = "programId";
    private static final String EXERCISE_ID = "exerciseId";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ExerciseDto exerciseDto = makeExercise(request);
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        exerciseDtoService.save(exerciseDto);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            setCLientId(request);
        }
        return new CommandResult(profilePage,true);
    }

    private ExerciseDto makeExercise(HttpServletRequest request) throws ServiceException {
        ExerciseDto exerciseDto = new ExerciseDto();
        int repeats = Integer.parseInt(request.getParameter(REPEATS));
        exerciseDto.setRepeatNumber(repeats);
        int setNumber = Integer.parseInt(request.getParameter(SET_NUMBER));
        exerciseDto.setSetNumber(setNumber);
        int trainDay = Integer.parseInt(request.getParameter(TRAIN_DAY));
        exerciseDto.setNumberTrainDay(trainDay);
        long programId = Long.parseLong(request.getParameter(PROGRAM_ID));
        exerciseDto.setProgramId(programId);
        ExerciseService service = new ExerciseService();
        long exerciseId = Long.parseLong(request.getParameter(EXERCISE_ID));
        Optional<Exercise> exercise = service.findById(exerciseId);
        exerciseDto.setExercise(exercise.get());
        return exerciseDto;
    }

    private void setCLientId(HttpServletRequest request) throws ServiceException {
        long programId = Long.parseLong(request.getParameter(PROGRAM_ID));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByProgramId(programId);
        clientOptional.ifPresent(client -> profilePage+=client.getId());
    }
}
