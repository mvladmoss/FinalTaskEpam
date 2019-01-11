package com.epam.fitness.model;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable,Identifiable {

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

    //For Tests maybe
    public static final String ID = "id_client";
    public static final String ID_COACH = "coach_id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String VISITS_NUMBER = "visits_number";
    public static final String PERSONAL_SALE = "personal_sale";
    public static final String CORPORATE_SALE = "corporate_sale";
    public static final String ID_PROGRAM = "program_id";


    public Client(Long id, Long coachId, String name, String surname, String login, String password, Integer visitNumber, Float personalSale, Float corporateSale, Long programId){
        this.id = id;
        this.coachId = coachId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.visitNumber = visitNumber;
        this.personalSale = personalSale;
        this.corporateSale = corporateSale;
        this.programId = programId;
    }

    public Client(){}

    public void setID(long clientID) {
        this.id = clientID;
    }

    public void setCoachId(Long coachId) {
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

    public Long getId() {
        return id;
    }

    public Long getCoachId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return  Objects.equals(getId(), client.getId()) &&
                Objects.equals(getCoachId(), client.getCoachId()) &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getSurname(), client.getSurname()) &&
                Objects.equals(getLogin(), client.getLogin()) &&
                Objects.equals(getPassword(), client.getPassword()) &&
                Objects.equals(getVisitNumber(), client.getVisitNumber()) &&
                Objects.equals(getPersonalSale(), client.getPersonalSale()) &&
                Objects.equals(getCorporateSale(), client.getCorporateSale()) &&
                Objects.equals(getProgramId(), client.getProgramId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getCoachId(),getName(),getSurname(),getLogin(),getPassword(),getVisitNumber(),getPersonalSale(),getCorporateSale(),getProgramId());
    }
}
