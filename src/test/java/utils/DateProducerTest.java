package utils;

import com.epam.fitness.utils.DateProducer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateProducerTest {

    @Test(dataProvider = "dataPassword")
    public void shouldValidatePassword(Date date, Integer daysToAdd) {
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR,daysToAdd);
        Date actualDate = calendar.getTime();
        //when
        Date expectedDate = DateProducer.getNecessaryDate(date,daysToAdd);
        //then
        assertEquals(expectedDate,actualDate);
    }

    @DataProvider(name = "dataPassword")
    public Object[][] DataForPasswordValidation(){
        return new Object[][]{
                {new Date(),14},
                {new Date(),42},
                {new Date(),4},
                {new Date(),0},
                {new Date(),54}
        };
    }

}
