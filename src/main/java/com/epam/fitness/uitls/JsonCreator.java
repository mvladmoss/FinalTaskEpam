package com.epam.fitness.uitls;

import com.epam.fitness.model.Exercise;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class JsonCreator {
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
}
