package com.epam.fitness.command.exercise;

import com.epam.fitness.command.Command;
import com.epam.fitness.command.CommandResult;
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


public class AddExerciseCommand implements Command {

    private String profilePage = "/controller?command=show_client_exercises&client_id=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ExerciseDto exerciseDto = makeExercise(request);
        ExerciseDtoService exerciseDtoService = new ExerciseDtoService();
        exerciseDtoService.save(exerciseDto);
        HttpSession session = request.getSession();
        if(session.getAttribute("role").equals("coach")){
            setCLientId(request);
        }
        return new CommandResult(profilePage,true);
    }

    private ExerciseDto makeExercise(HttpServletRequest request) throws ServiceException {
        ExerciseDto exerciseDto = new ExerciseDto();
        int repeats = Integer.parseInt(request.getParameter("repeats"));
        exerciseDto.setRepeatNumber(repeats);
        int setNumber = Integer.parseInt(request.getParameter("setNumber"));
        exerciseDto.setSetNumber(setNumber);
        int trainDay = Integer.parseInt(request.getParameter("trainDay"));
        System.out.println(trainDay);
        exerciseDto.setNumberTrainDay(trainDay);
        long programId = Long.parseLong(request.getParameter("programId"));
        exerciseDto.setProgramId(programId);
        ExerciseService service = new ExerciseService();
        long exerciseId = Long.parseLong(request.getParameter("exerciseId"));
        Optional<Exercise> exercise = service.findById(exerciseId);
        exerciseDto.setExercise(exercise.get());
        return exerciseDto;
    }

    private void setCLientId(HttpServletRequest request) throws ServiceException {
        long programId = Long.parseLong(request.getParameter("programId"));
        ClientService clientService = new ClientService();
        Optional<Client> clientOptional = clientService.findByProgramId(programId);
        clientOptional.ifPresent(client -> profilePage+=client.getId());
    }
}
