package utils;

import com.epam.fitness.utils.DataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataReaderTest {
    private final static String DATA_PATH  = "prices.json";
    private DataReader reader;

    @BeforeClass
    public void init(){
        reader = new DataReader();
    }

    @Test
    public void testReadJsonFile() {
        //give
         String expectedJsonString = "[\n" + "  {\n" +  "    \"days\": 14,\n" +  "    \"price\": 10.0\n" +  "  },\n" +  "  {\n" +  "    \"days\": 30,\n" +  "    \"price\": 15.0\n" +  "  },\n" +  "\n" +  "  {\n" + "    \"days\": 60,\n" +  "    \"price\": 20.0\n" + "  },\n" + "\n" + "  {\n" + "    \"days\": 90,\n" + "    \"price\": 25.0\n" +"  }\n" +"]";
         //when
        String actualJsonString = reader.read(DATA_PATH);
        actualJsonString = actualJsonString.trim();
        //then
        Assert.assertEquals(expectedJsonString, actualJsonString);
    }

}
