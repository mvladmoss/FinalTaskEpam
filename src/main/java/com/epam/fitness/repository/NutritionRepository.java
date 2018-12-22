package com.epam.fitness.repository;

import com.epam.fitness.builder.NutritionBuilder;
import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Nutrition;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class NutritionRepository extends AbstractRepository {

    private static final String TABLE_NAME = "nutrition";

    public NutritionRepository(Connection connection) { super(connection); }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Nutrition> query(SqlSpecification specification) throws RepositoryException{
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
}
