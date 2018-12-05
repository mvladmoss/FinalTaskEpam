package com.epam.fitness.repository;

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

    @Override
    public void add(Client client) throws RepositoryException {
    }

    @Override
    public void delete(Client user) throws RepositoryException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
            statement.setObject(1, user.getName());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Client entity) throws RepositoryException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);
            statement.setObject(1, entity.getName());
            statement.setObject(2, entity.getSurname());
            statement.setObject(3, entity.getLogin());
            statement.setObject(4, entity.getPassword());
            statement.setObject(5, entity.getVisitNumber());
            statement.setObject(6, entity.getSale());
            statement.setObject(7, entity.isCorporate());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> executeQuery(SqlSpecification specification) throws RepositoryException {
        List<Client> users = new ArrayList<>();
        String sql = specification.getSql();
        try (ProxyConnection dbConnection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement statement = dbConnection.prepareStatement(sql);

            for (int index = 0; index < specification.getParameters().size(); index++) {
                statement.setObject(index + 1, specification.getParameters().get(index));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client user = createClient(resultSet,specification);
                users.add(user);
            }
            statement.close();

            return users;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<Client> executeQueryForSingleResult(SqlSpecification specification) throws RepositoryException {

        List<Client> items = executeQuery(specification);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }


    private Client createClient(ResultSet resultSet, SqlSpecification specification) throws SQLException {
        Client client = new Client();

        if(specification.isFieldRequired("id")){
            long clientId = resultSet.getInt("id");
            client.setClientID(clientId);
        }

        if(specification.isFieldRequired("coach_id")){
            int coachId = resultSet.getInt("coach_id");
            client.setCoachId(coachId);
        }

        if(specification.isFieldRequired("name")){
            String name = resultSet.getString("name");
            client.setName(name);
        }

        if(specification.isFieldRequired("surname")){
            String surname = resultSet.getString("surname");
            client.setSurname(surname);
        }

        if(specification.isFieldRequired("password")){
            String password = resultSet.getString("password");
            client.setPassword(password);
        }

        if(specification.isFieldRequired("visits_number")){
            int visitsNumber = resultSet.getInt("visits_number");
            client.setVisitNumber(visitsNumber);
        }

        if(specification.isFieldRequired("sale")){
            Float sale = resultSet.getFloat("sale");
            client.setSale(sale);
        }


        if(specification.isFieldRequired("isCorporate")){
            boolean isCorporate = resultSet.getBoolean("isCorporate");
            client.setCorporate(isCorporate);
        }

        return client;
    }
}
