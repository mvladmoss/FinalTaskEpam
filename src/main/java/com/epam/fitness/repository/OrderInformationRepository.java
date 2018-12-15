package com.epam.fitness.repository;

import com.epam.fitness.builder.OrderInformationBuilder;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.specifications.SqlSpecification;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderInformationRepository extends AbstractRepository<OrderInformation> {


    private static final String TABLE_NAME = "order_information";
    private final static String INSERT_QUERY = "insert into order_information (id_order_information,cost,payment_data,end_date,client_id)" +
            " values(?,?,?,?,?) on duplicate key " +
            "update id_order_information = values(id_order_information), cost = values(cost), payment_data = values(payment_data)," +
            "end_date = values(end_date), client_id = values(client_id)";


    public OrderInformationRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }


    @Override
    public List<OrderInformation> query(SqlSpecification specification) throws RepositoryException{
        String query = "select * from order_information " + specification.getSql();
        List<OrderInformation> orderInformation = executeQuery(query,new OrderInformationBuilder(), specification.getParameters());
        return orderInformation;
    }

    @Override
    public Optional<OrderInformation> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        List<OrderInformation> coach = query(specification);
        return coach.size() == 1 ?
                Optional.of(coach.get(0)) :
                Optional.empty();
    }


    public Long save(OrderInformation order) throws RepositoryException {
        BigDecimal cost = order.getCost();
        String costString = String.valueOf(cost);
        Timestamp paymentData = order.getPaymenData();
        String paymentDataString = String.valueOf(paymentData);
        Date endDate = order.getTrainEndDate();
        String endDateString = String.valueOf(endDate);
        long clientId = order.getClientId();
        String clientIdString = String.valueOf(clientId);
        return executeUpdate(INSERT_QUERY,Arrays.asList(null,costString,paymentDataString,endDateString,clientIdString));

    }




}
