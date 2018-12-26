package com.epam.fitness.uitls.json;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.uitls.json.JsonCreator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;

public class JsonExerciseCreator implements JsonCreator<Exercise> {
    public String makeJSON(List<Exercise> exercises){
        JSONObject mainObject = new JSONObject();
        JSONArray exerciseArray = new JSONArray();
        for(Exercise exercise : exercises) {
            JSONObject object = new JSONObject();
            object.put("id",exercise.getId());
            object.put("equipmentId",exercise.getEquipmentId());
            object.put("name",exercise.getName());
            object.put("description",exercise.getDescription());
            exerciseArray.add(object);
        }
        mainObject.put("exercises",exerciseArray);
        return mainObject.toJSONString();
    }

    public static void main(String[] args) {
        final Long ID_EXERCISE1 = 1l;
        final Long ID_EQUIPMENT = 2l;
        final String NAME = "easyProgram";
        final String DESCRIPTION = "asdasdas";
        final Exercise EXERCISE = new Exercise(ID_EXERCISE1, ID_EQUIPMENT, NAME, DESCRIPTION);
        System.out.println(new JsonExerciseCreator().makeJSON(Arrays.asList(EXERCISE)));
    }
}
