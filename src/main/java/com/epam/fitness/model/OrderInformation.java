package com.epam.fitness.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class OrderInformation {

    private Long id;
    private BigDecimal cost;
    private Timestamp paymenData;
    private Date trainEndDate;
    private Long clientId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Timestamp getPaymenData() {
        return paymenData;
    }

    public void setPaymenData(Timestamp paymenData) {
        this.paymenData = paymenData;
    }

    public Date getTrainEndDate() {
        return trainEndDate;
    }

    public void setTrainEndDate(Date trainEndDate) {
        this.trainEndDate = trainEndDate;
    }


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
