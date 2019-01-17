package com.epam.fitness.controller;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.utils.json.JsonCreator;
import com.epam.fitness.utils.json.JsonExerciseCreator;
import com.epam.fitness.utils.search.SearchExerciseSystem;
import com.epam.fitness.utils.search.SearchSystem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AjaxController extends HttpServlet {

    private SearchSystem searchSystem = new SearchExerciseSystem();
    private JsonCreator jsonCreator = new JsonExerciseCreator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req,resp);
    }

    private void proccessRequest(HttpServletRequest request,HttpServletResponse response){
        String currentSearchArgument = request.getParameter("searchArgument");
        List<Exercise> exercises = getAllExercises();
        List<Exercise> exercisesAppropriateToSearchArgument = searchSystem.findItemsAppropriateToSearchArgument(exercises,currentSearchArgument);
        String exercisesInJson = jsonCreator.makeJSON(exercisesAppropriateToSearchArgument);
        writeResponse(response,exercisesInJson);
    }

    private List<Exercise> getAllExercises(){
        ExerciseService exerciseService = new ExerciseService();
        List<Exercise> exercises = new ArrayList<>();
        try {
            exercises = exerciseService.findAll();
        }catch (ServiceException e) {
            //commandResult = new CommandResult(ERROR_PAGE, false);
        }
        return exercises;
    }

    private void writeResponse(HttpServletResponse response,String exerciseInJson){
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        out.print(exerciseInJson);
    }

}
