package com.epam.fitness.builder;

import com.epam.fitness.model.Client;
import com.epam.fitness.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<Client> {

    @Override
    public Client build(ResultSet resultSet) throws RepositoryException {
        Client client = new Client();
        //constructor
        try {
            Long id = resultSet.getLong("id_client");
            client.setID(id);
            Long coach_id = resultSet.getLong("coach_id");
            client.setCoachId(coach_id);
            String name = resultSet.getString("name");
            client.setName(name);
            String surname = resultSet.getString("surname");
            client.setSurname(surname);
            String login = resultSet.getString("login");
            client.setLogin(login);
            String password = resultSet.getString("password");
            client.setPassword(password);
            int visits_number = resultSet.getInt("visits_number");
            client.setVisitNumber(visits_number);
            float personalSale = resultSet.getFloat("personal_sale");
            client.setPersonalSale(personalSale);
            float corporateSale = resultSet.getFloat("corporate_sale");
            client.setCorporateSale(corporateSale);
            Long programId = resultSet.getLong("program_id");
            client.setProgramId(programId);
            return client;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
