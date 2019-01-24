package utils;

import com.epam.fitness.utils.RequestParameterValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class RequestParameterValidatorTest {

    private static RequestParameterValidator VALIDATOR = new RequestParameterValidator();
    private final static boolean VALID = true;
    private final static boolean INVALID = false;


    @Test(dataProvider = "dataPassword")
    public void shouldValidatePassword(String password, boolean isValid) {
        assertEquals(VALIDATOR.isPasswordValid(password),isValid);
    }

    @DataProvider(name = "dataPassword")
    public Object[][] DataForPasswordValidation(){
        return new Object[][]{
                {"",INVALID},
                {"<>asd32",INVALID},
                {"12",INVALID},
                {"3423vasd",VALID},
                {"_.asde",VALID}
        };
    }

    @Test(dataProvider = "dataLogin")
    public void shouldValidateLogin(String login, boolean isValid) {
        assertEquals(VALIDATOR.isLoginValid(login),isValid);
    }

    @DataProvider(name = "dataLogin")
    public Object[][] DataForLoginValidation(){
        return new Object[][]{
                {"1sdsa",INVALID},
                {"vladmoss",VALID},
                {"ValidLoginName",VALID},
                {"<script>alert(4)</script>",INVALID},
                {"javascript:(alert)",INVALID}
        };
    }

    @Test(dataProvider = "dataInputText")
    public void shouldValidateInputText(String login, boolean isValid) {
        assertEquals(VALIDATOR.isNutritionDescriptionValid(login),isValid);
    }

    @DataProvider(name = "dataInputText")
    public Object[][] DataForInputTextValidation(){
        return new Object[][]{
                {"<script>",INVALID},
                {"1234123412341234asdasdwsadasdad",VALID},
                {"1)milk.,(2)tea",VALID},
                {"_meat*",INVALID},
                {"asdasdasdew",VALID}
        };
    }

    @Test(dataProvider = "dataCardNumber")
    public void shouldValidateCardNumber(String login, boolean isValid) {
        assertEquals(VALIDATOR.isCardNumberValid(login),isValid);
    }

    @DataProvider(name = "dataCardNumber")
    public Object[][] DataForCardNumberValidation(){
        return new Object[][]{
                {"<script>",INVALID},
                {"1234123412341234",VALID},
                {"dsac",INVALID},
                {"2312321",INVALID},
                {"-=32fds2d",INVALID}
        };
    }

    @Test(dataProvider = "dataNameAndSurnameTest")
    public void shouldValidateNameAndSurname(String name, boolean isValid) {
        assertEquals(VALIDATOR.isNameValid(name),isValid);
    }

    @DataProvider(name = "dataNameAndSurnameTest")
    public Object[][] DataForNameAndSurnameValidation(){
        return new Object[][]{
                {"<asdsa>32%#",INVALID},
                {"Vlad",VALID},
                {"Moskovkin",VALID},
                {"3Vlad",INVALID},
                {"_dsae<script>",INVALID}
        };
    }

    @Test(dataProvider = "dataForSetAndNumberRepeats")
    public void shouldValidateSetNumberAndRepeats(String number, boolean isValid) {
        assertEquals(VALIDATOR.isSetNumberValid(number),isValid);
    }

    @DataProvider(name = "dataForSetAndNumberRepeats")
    public Object[][] DataForSetNumberAndRepeatsValidation(){
        return new Object[][]{
                {"123",INVALID},
                {"1",VALID},
                {"32",VALID},
                {"0",INVALID},
                {"-2",INVALID}
        };
    }

}
