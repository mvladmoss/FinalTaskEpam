package com.epam.fitness.builder;

import com.epam.fitness.model.Coach;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachBuilder implements Builder<Coach> {
    @Override
    public Coach build(ResultSet resultSet) {
        Coach coach = new Coach();
        try {
            long id = resultSet.getInt("id_coach");
            coach.setId(id);
            String name = resultSet.getString("name");
            coach.setName(name);
            String surname = resultSet.getString("surname");
            coach.setSurname(surname);
            String login = resultSet.getString("login");
            coach.setLogin(login);
            String password = resultSet.getString("password");
            coach.setPassword(password);
        }catch (SQLException exception){
            //Log
            throw  new RuntimeException();
        }
        return coach;
    }
}
