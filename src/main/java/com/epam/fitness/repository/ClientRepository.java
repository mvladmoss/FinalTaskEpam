package com.epam.fitness.repository;

import com.epam.fitness.builder.ClientBuilder;
import com.epam.fitness.connection.ConnectionPool;
import com.epam.fitness.connection.ConnectionPoolException;
import com.epam.fitness.connection.ProxyConnection;
import com.epam.fitness.model.Client;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository extends AbstractRepository<Client> {


    private final String UPDATE_USER_SQL = "UPDATE `client` SET `name` = ?," +
            "`surname` = ?,`login` = ?,`password` = ?,`visits_number` = ?,`sale` = ?,`isCorporate` = ? WHERE `id` = ?;";

    private final String DELETE_USER_SQL = "DELETE FROM `client` WHERE `id` = ?;";

    private static final String CLIENT_AND_PASSWORD_QUERY = "SELECT * FROM user WHERE username = ? ";

    public ClientRepository(Connection connection){
        super(connection);
    }


    public Optional<Client> findClientByLogin(String login, String password) throws RepositoryException {

        return executeQueryForSingleResult(CLIENT_AND_PASSWORD_QUERY, new ClientBuilder(), login,password);

    }

    @Override
    public void add(Client entity) throws RepositoryException {

    }

    @Override
    public void delete(Client entity) throws RepositoryException {

    }

    @Override
    public void update(Client entity) throws RepositoryException {

    }

    @Override
    public List<Client> executeQuery(SqlSpecification specification) throws RepositoryException, ConnectionPoolException {
        return null;
    }

}
