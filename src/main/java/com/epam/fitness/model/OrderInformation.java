package com.epam.fitness.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class OrderInformation implements Identifiable{

    private Long id;
    private BigDecimal cost;
    private Timestamp paymentData;
    private Date membershipEndDate;
    private Long clientId;

    public Long getId() {
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

    public Timestamp getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(Timestamp paymentData) {
        this.paymentData = paymentData;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
