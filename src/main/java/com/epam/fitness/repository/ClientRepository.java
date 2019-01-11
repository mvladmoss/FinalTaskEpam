package com.epam.fitness.repository;

import com.epam.fitness.builder.ClientBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.Client;
import com.epam.fitness.repository.specifications.SqlSpecification;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientRepository extends AbstractRepository<Client> {

    private static final String TABLE_NAME = "client";
    private final static String INSERT_QUERY = "insert into client (id_client,coach_id,name,surname,login,password,visits_number," +
            "personal_sale,corporate_sale,program_id)" +
            "values(?,?,?,?,?,?,?,?,?,?) " +
            "on duplicate key " +
            "update id_client = values(id_client), coach_id = values(coach_id), name = values(name)," +
            "surname = values(surname), login = values(login), password = values(password)," +
            "visits_number = values(visits_number), personal_sale = values(personal_sale)," +
            "corporate_sale = values(corporate_sale), program_id = values(program_id)";

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

    public Long save(Client client) throws RepositoryException {
        Long clientId = client.getId();
        Long coachId = client.getCoachId();

        String name = client.getName();
        String surname = client.getSurname();
        String login = client.getLogin();
        String password = client.getPassword();

        int visitsNumber = client.getVisitNumber();

        float personalSale = client.getPersonalSale();
        float corporateSale = client.getCorporateSale();
        long programId = client.getProgramId();

        return executeUpdate(INSERT_QUERY,Arrays.asList(clientId,coachId,name,surname,login,password,visitsNumber,personalSale,corporateSale,programId));
    }




}
