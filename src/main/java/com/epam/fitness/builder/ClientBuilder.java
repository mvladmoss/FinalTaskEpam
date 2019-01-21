package com.epam.fitness.builder;

import com.epam.fitness.model.Client;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.repository.database.constants.ClientTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<Client> {

    @Override
    public Client build(ResultSet resultSet) throws RepositoryException {
        Client client = new Client();
        //constructor
        try {
            Long id = resultSet.getLong(ClientTableConstants.ID.getFieldName());
            client.setID(id);
            Long coach_id = resultSet.getLong(ClientTableConstants.COACH_ID.getFieldName());
            client.setCoachId(coach_id);
            String name = resultSet.getString(ClientTableConstants.NAME.getFieldName());
            client.setName(name);
            String surname = resultSet.getString(ClientTableConstants.SURNAME.getFieldName());
            client.setSurname(surname);
            String login = resultSet.getString(ClientTableConstants.LOGIN.getFieldName());
            client.setLogin(login);
            String password = resultSet.getString(ClientTableConstants.PASSWORD.getFieldName());
            client.setPassword(password);
            int membershipPurchasedNumber = resultSet.getInt(ClientTableConstants.MEMBERSHIP_PURCHASED_NUMBER.getFieldName());
            client.setMembershipPurchasedNumber(membershipPurchasedNumber);
            float personalDiscount = resultSet.getFloat(ClientTableConstants.PERSONAL_DISCOUNT.getFieldName());
            client.setPersonalSale(personalDiscount);
            float corporateDiscount = resultSet.getFloat(ClientTableConstants.CORPORATE_DISCOUNT.getFieldName());
            client.setCorporateSale(corporateDiscount);
            Long programId = resultSet.getLong(ClientTableConstants.PROGRAM_ID.getFieldName());
            client.setProgramId(programId);
            return client;
        }catch (SQLException exception){
            throw new RepositoryException(exception.getMessage(),exception);
        }
    }
}
