package com.epam.fitness.model;

import java.io.Serializable;

public class Client implements Serializable {

    private Long id;
    private Long coachId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Integer visitNumber;
    private Float personalSale;
    private Float corporateSale;
    private Long programId;

    public void setID(long clientID) {
        this.id = clientID;
    }

    public void setCoachId(long coachId) {
        this.coachId = coachId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public void setPersonalSale(float personalSale) {
        this.personalSale = personalSale;
    }

    public void setCorporateSale(float corporateSale) {
        this.corporateSale = corporateSale;
    }

    public long getId() {
        return id;
    }

    public long getCoachId() {
        return coachId;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public float getCorporateSale() {
        return corporateSale;
    }

    public float getPersonalSale() {
        return personalSale;
    }

    public String getName() {
        return name;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }
}
