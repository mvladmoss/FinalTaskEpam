package com.epam.fitness.uitls.search;

import com.epam.fitness.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class SearchExerciseSystem implements SearchSystem<Exercise> {

    private final static String NULL = "null";

    public List<Exercise> findItemsAppropriateToSearchArgument(List<Exercise> exercisesToCheck, String searchArgument){
        if(searchArgument.equals(NULL)){
            return exercisesToCheck;
        }
        List<Exercise> finalListExercise = new ArrayList<>();
        for(Exercise exercise : exercisesToCheck){
            if(exerciseToLowerCase(exercise).contains(searchArgument.toLowerCase())){
                finalListExercise.add(exercise);
            }
        }
        return finalListExercise;
    }

    private String exerciseToLowerCase(Exercise exercise){
        String exerciseStringName = exercise.getName();
        return exerciseStringName.toLowerCase();
    }
}
