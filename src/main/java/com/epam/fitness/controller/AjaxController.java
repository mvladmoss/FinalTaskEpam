package com.epam.fitness.controller;

import com.epam.fitness.exception.ServiceException;
import com.epam.fitness.model.Exercise;
import com.epam.fitness.service.ExerciseService;
import com.epam.fitness.uitls.JsonCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AjaxController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req,resp);
    }

    private void proccessRequest(HttpServletRequest request,HttpServletResponse response){
        System.out.println("Here");
        String currentSearchArgument = request.getParameter("searchArgument");
        ExerciseService exerciseService = new ExerciseService();
        List<Exercise> exercises = new ArrayList<>();
        try {
            exercises = exerciseService.findAll();
        } catch (ServiceException e) {
            throw new RuntimeException();
        }
        JsonCreator creator = new JsonCreator();
        String exercisesJson = creator.makeJSON(exercises);
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        out.print(exercisesJson);

    }

}
