package com.epam.fitness.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class OrderInformation {

    private Long id;
    private BigDecimal cost;
    private Timestamp paymenData;
    private Date trainStartDate;
    private Date trainEndDate;
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

    public Date getTrainStartDate() {
        return trainStartDate;
    }

    public void setTrainStartDate(Date trainStartDate) {
        this.trainStartDate = trainStartDate;
    }

    public Date getTrainEndDate() {
        return trainEndDate;
    }

    public void setTrainEndDate(Date trainEndDate) {
        this.trainEndDate = trainEndDate;
    }


}
