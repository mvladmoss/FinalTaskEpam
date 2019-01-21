package com.epam.fitness.builder;

import com.epam.fitness.model.Coach;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.database.constants.CoachTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachBuilder implements Builder<Coach> {
    @Override
    public Coach build(ResultSet resultSet) throws RepositoryException {
        Coach coach = new Coach();
        try {
            long id = resultSet.getInt(CoachTableConstants.ID.getFieldName());
            coach.setId(id);
            String name = resultSet.getString(CoachTableConstants.NAME.getFieldName());
            coach.setName(name);
            String surname = resultSet.getString(CoachTableConstants.SURNAME.getFieldName());
            coach.setSurname(surname);
            String login = resultSet.getString(CoachTableConstants.LOGIN.getFieldName());
            coach.setLogin(login);
            String password = resultSet.getString(CoachTableConstants.PASSWORD.getFieldName());
            coach.setPassword(password);
            return coach;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
