package com.epam.fitness.repository;

import com.epam.fitness.builder.ClientBuilder;
import com.epam.fitness.builder.CoachBuilder;
import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.model.Client;
import com.epam.fitness.model.Coach;
import com.epam.fitness.repository.creator.RepositoryCreator;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CoachRepository extends AbstractRepository<Coach> {

    private static final String TABLE_NAME = "coach";



    public CoachRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public List<Coach> query(SqlSpecification specification) throws RepositoryException{
        String query = "select * from coach " + specification.getSql();
        List<Coach> coaches = executeQuery(query,new CoachBuilder(), specification.getParameters());
        return coaches;
    }

    @Override
    public Optional<Coach> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<Coach> coach = query(specification);
        return coach.size() == 1 ?
                Optional.of(coach.get(0)) :
                Optional.empty();
    }




}
