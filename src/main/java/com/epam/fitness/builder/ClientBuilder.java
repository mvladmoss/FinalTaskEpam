package com.epam.fitness.builder;

import com.epam.fitness.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<Client>{

    @Override
    public Client build(ResultSet resultSet) {
        Client client = new Client();
        try {
            long id = resultSet.getInt("id");
            client.setID(id);
            String name = resultSet.getString("name");
            client.setName(name);
            String surname = resultSet.getString("surname");
            client.setSurname("surname");
            String login = resultSet.getString("login");
            client.setLogin(login);
            String password = resultSet.getString("password");
            client.setPassword(password);
            int visits_number = resultSet.getInt("visits_number");
            client.setVisitNumber(visits_number);
            float sale = resultSet.getFloat("sale");
            client.setSale(sale);
            boolean isCorporate = resultSet.getBoolean("isCorporate");
            client.setCorporate(isCorporate);
        }catch (SQLException exception){
            //ASK
        }
    }
}
