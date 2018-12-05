package com.epam.fitness.repository.specifications.order;

import com.epam.fitness.repository.specifications.SqlSpecification;

import java.util.ArrayList;
import java.util.List;

public class findLastClientOrderInformationByClientId implements SqlSpecification {
    private static final String SQL = "select order_information.id,cost,payment_data,start_date,end_date " +
            "from order_information inner join client_order on order_information.client_order_id=client_order.id " +
            "where client_order.client_id=? order by end_date desc limit 1";
    private List<Object> parameters = new ArrayList<>();
    private ArrayList<String> fields = new ArrayList<>();

    public findLastClientOrderInformationByClientId(long id) {
        parameters.add(id);
        fields.add("id");
        fields.add("cost");
        fields.add("payment_date");
        fields.add("start_date");
        fields.add("end_date");
    }

    @Override
    public String getSql() {
        return SQL;
    }

    @Override
    public List<Object> getParameters() {
        return parameters;
    }

    @Override
    public boolean isFieldRequired(String filed) {
        return fields.contains(filed);
    }
}