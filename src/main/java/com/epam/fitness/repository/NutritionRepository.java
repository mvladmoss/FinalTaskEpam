package com.epam.fitness.repository;

import com.epam.fitness.builder.NutritionBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NutritionRepository extends AbstractRepository {

    private final static String INSERT_QUERY = "insert into nutrition (id_nutrition,name,description,morning_nutrition,lunch_nutrition,dinner_nutrition) " +
            "values(?,?,?,?,?,?) " +
            "on duplicate key " +
            "update id_nutrition = values(id_nutrition), name = values(name), description = values(description),morning_nutrition = values(morning_nutrition)," +
            "lunch_nutrition = values(lunch_nutrition),dinner_nutrition = values(dinner_nutrition)";

    private static final String TABLE_NAME = "nutrition";

    public NutritionRepository(Connection connection) { super(connection); }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Nutrition> query(SqlSpecification specification) throws RepositoryException {
        String query = "select * from nutrition " + specification.getSql();
        List<Nutrition> nutrition = executeQuery(query,new NutritionBuilder(), specification.getParameters());
        return nutrition;
    }

    @Override
    public Optional<Nutrition> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<Nutrition> nutrition = query(specification);
        return nutrition.size() == 1 ?
                Optional.of(nutrition.get(0)) :
                Optional.empty();
    }

    public Long save(Nutrition nutrition) throws RepositoryException {
        Long id = nutrition.getId();
        String name = nutrition.getName();
        String desciption = nutrition.getDescription();
        String morningNutrition = nutrition.getMorningNutrition();
        String lunchNutrition = nutrition.getLunchNutrition();
        String dinnerNutrition = nutrition.getDinnerNutrition();
        return executeUpdate(INSERT_QUERY,Arrays.asList(id,name,desciption,morningNutrition,lunchNutrition,dinnerNutrition));
    }

}
