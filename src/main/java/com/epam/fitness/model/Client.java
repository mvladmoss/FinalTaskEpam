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
    private Integer membership_purchased_number;
    private Float personalDiscount;
    private Float corporateDiscount;
    private Long programId;

    //For Tests maybe
    public static final String ID = "id_client";
    public static final String ID_COACH = "coach_id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String MEMBERSHIP_PURCHASED_NUMBER = "membership_purchased_number";
    public static final String PERSONAL_DISCOUNT = "personal_discount";
    public static final String CORPORATE_DISCOUNT = "corporate_discount";
    public static final String ID_PROGRAM = "program_id";


    public Client(Long id, Long coachId, String name, String surname, String login, String password, Integer membership_purchased_number, Float personalSale, Float corporateSale, Long programId){
        this.id = id;
        this.coachId = coachId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.membership_purchased_number = membership_purchased_number;
        this.personalDiscount = personalSale;
        this.corporateDiscount = corporateSale;
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

    public void setMembershipPurchasedNumber(int membership_purchased_number) {
        this.membership_purchased_number = membership_purchased_number;
    }

    public void setPersonalSale(float personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public void setCorporateSale(float corporateDiscount) {
        this.corporateDiscount = corporateDiscount;
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

    public int getMembershipPurchasedNumber() {
        return membership_purchased_number;
    }

    public float getCorporateDiscount() {
        return corporateDiscount;
    }

    public float getPersonalDiscount() {
        return personalDiscount;
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
                Objects.equals(getMembershipPurchasedNumber(), client.getMembershipPurchasedNumber()) &&
                Objects.equals(getPersonalDiscount(), client.getPersonalDiscount()) &&
                Objects.equals(getCorporateDiscount(), client.getCorporateDiscount()) &&
                Objects.equals(getProgramId(), client.getProgramId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getCoachId(),getName(),getSurname(),getLogin(),getPassword(),getMembershipPurchasedNumber(),getPersonalDiscount(),getCorporateDiscount(),getProgramId());
    }

}
