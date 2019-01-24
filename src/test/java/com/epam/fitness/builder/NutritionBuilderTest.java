package com.epam.fitness.builder;

import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Coach;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.repository.database.constants.CoachTableConstants;
import com.epam.fitness.repository.database.constants.NutritionTableConstants;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NutritionBuilderTest {

    private static final Long ID_NUTRITION = 1L;
    private static final String NAME = "Vlad";
    private static final String MORNING_NUTRITION = "asdasd";
    private static final String LUNCH_NUTRITION = "asdsasss";
    private static final String DINNER_NUTRITION = "asde";

    private static final Nutrition EXPECTED_NUTRITION = new Nutrition(
            ID_NUTRITION,
            NAME,
            MORNING_NUTRITION,
            LUNCH_NUTRITION,
            DINNER_NUTRITION);

    @Test
    public void shouldBuildAndReturnNutritionWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(NutritionTableConstants.ID.getFieldName())).thenReturn(ID_NUTRITION);
        when(resultSet.getString(NutritionTableConstants.NAME.getFieldName())).thenReturn(NAME);
        when(resultSet.getString(NutritionTableConstants.MORNING_NUTRITION.getFieldName())).thenReturn(MORNING_NUTRITION);
        when(resultSet.getString(NutritionTableConstants.LUNCH_NUTRITION.getFieldName())).thenReturn(LUNCH_NUTRITION);
        when(resultSet.getString(NutritionTableConstants.DINNER_NUTRITION.getFieldName())).thenReturn(DINNER_NUTRITION);

        NutritionBuilder nutritionBuilder = new NutritionBuilder();
        Nutrition actualNutrition = nutritionBuilder.build(resultSet);
        Assert.assertEquals(EXPECTED_NUTRITION, actualNutrition);

    }
}
