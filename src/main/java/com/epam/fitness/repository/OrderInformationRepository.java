package com.epam.fitness.repository;

import com.epam.fitness.builder.OrderInformationBuilder;
import com.epam.fitness.exception.RepositoryException;
import com.epam.fitness.model.OrderInformation;
import com.epam.fitness.repository.database.constants.OrderInformationTableConstants;
import com.epam.fitness.repository.specifications.SqlSpecification;
import java.sql.Connection;
import java.util.*;

public class OrderInformationRepository extends AbstractRepository<OrderInformation> {


    private static final String TABLE_NAME = "order_information";

    public OrderInformationRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }


    @Override
    public List<OrderInformation> query(SqlSpecification specification) throws RepositoryException {
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

    @Override
    protected Map<String, Object> getFields(OrderInformation orderInformation) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(OrderInformationTableConstants.ID.getFieldName(),orderInformation.getId());
        fields.put(OrderInformationTableConstants.COST.getFieldName(),orderInformation.getCost());
        fields.put(OrderInformationTableConstants.PAYMENT_DATA.getFieldName(),orderInformation.getPaymentData());
        fields.put(OrderInformationTableConstants.MEMBERSHIP_END_DATE.getFieldName(),orderInformation.getMembershipEndDate());
        fields.put(OrderInformationTableConstants.CLIENT_ID.getFieldName(),orderInformation.getClientId());
        return fields;
    }

}
