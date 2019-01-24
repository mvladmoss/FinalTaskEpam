package utils;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.utils.json.JsonExerciseCreator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class JsonExerciseCreatorTest {

    private static final Long ID_EXERCISE1 = 1L;
    private static final String  NAME = "easyProgram";
    private static final String  DESCRIPTION = "asdasdas";
    private static final String IMAGE="gym.jpg";

    private final static Exercise EXERCISE = new Exercise(ID_EXERCISE1,NAME,DESCRIPTION,IMAGE);
    private final static String EXPECTED_JSON_STRING = "{\"exercises\":[{\"image\":\"gym.jpg\",\"name\":\"easyProgram\",\"description\":\"asdasdas\",\"id\":1}]}";
    private final static JsonExerciseCreator jsonCreator = new JsonExerciseCreator();

    @Test
    public void ShouldCreateJsonPresentationExercise(){
        String actualJsonString = jsonCreator.makeJSON(Collections.singletonList(EXERCISE));
        Assert.assertEquals(EXPECTED_JSON_STRING, actualJsonString);

    }

}
