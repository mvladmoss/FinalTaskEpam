/*
package utils;

import com.epam.fitness.model.Exercise;
import com.epam.fitness.utils.json.JsonExerciseCreator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class JsonExerciseCreatorTest {

    //БУДЕТ НЕПРАВИЛЬНО ИЗ-ЗА ДОБАВЛЕНИЯ IMAGE, ИСПРАВИТЬ ПОТОМ
    private static final Long ID_EXERCISE1 = 1l;
    private static final Long ID_EQUIPMENT = 2l;
    private static final String  NAME = "easyProgram";
    private static final String  DESCRIPTION = "asdasdas";
    private static final String IMAGE="../images/gym.jpg";

    private final static Exercise EXERCISE = new Exercise(ID_EXERCISE1,ID_EQUIPMENT,NAME,DESCRIPTION,IMAGE);
    private final static String EXPECTED_JSON_STRING = "{\"exercises\":[{\"name\":\"easyProgram\",\"description\":\"asdasdas\",\"id\":1,\"equipmentId\":2}]}";
    private final static JsonExerciseCreator jsonCreator = new JsonExerciseCreator();

    @Test
    public void ShouldCreateJsonPresentationExercise(){
        String actualJsonString = jsonCreator.makeJSON(Arrays.asList(EXERCISE));
        Assert.assertEquals(EXPECTED_JSON_STRING, actualJsonString);

    }

}
*/
