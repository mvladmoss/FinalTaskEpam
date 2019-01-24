package com.epam.fitness.repository;

import com.epam.fitness.builder.ClientBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Client;
import com.epam.fitness.repository.database.constants.ClientTableConstants;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.*;

public class ClientRepository extends AbstractRepository<Client> {

    private static final String TABLE_NAME = "client";

    public ClientRepository(Connection connection){
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }


    @Override
    public List<Client> query(SqlSpecification specification) throws RepositoryException {
        String query = "select * from client " + specification.getSql();
        List<Client> clients = executeQuery(query,new ClientBuilder(), specification.getParameters());
        return clients;
    }

    @Override
    public Optional<Client> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<Client> client = query(specification);
        return client.size() == 1 ?
                Optional.of(client.get(0)) :
                Optional.empty();
    }

    public Map<String,Object> getFields(Client client) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(ClientTableConstants.ID.getFieldName(), client.getId());
        fields.put(ClientTableConstants.COACH_ID.getFieldName(), client.getCoachId());
        fields.put(ClientTableConstants.NAME.getFieldName(), client.getName());
        fields.put(ClientTableConstants.SURNAME.getFieldName(), client.getSurname());
        fields.put(ClientTableConstants.LOGIN.getFieldName(), client.getLogin());
        fields.put(ClientTableConstants.PASSWORD.getFieldName(), client.getPassword());
        fields.put(ClientTableConstants.MEMBERSHIP_PURCHASED_NUMBER.getFieldName(), client.getMembershipPurchasedNumber());
        fields.put(ClientTableConstants.PERSONAL_DISCOUNT.getFieldName(), client.getPersonalDiscount());
        fields.put(ClientTableConstants.PROGRAM_ID.getFieldName(), client.getProgramId());

        return fields;
    }
}
