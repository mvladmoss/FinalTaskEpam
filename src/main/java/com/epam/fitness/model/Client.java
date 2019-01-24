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
    private Long programId;

    public Client(Long id, Long coachId, String name, String surname, String login, String password, Integer membership_purchased_number, Float personalSale, Long programId){
        this.id = id;
        this.coachId = coachId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.membership_purchased_number = membership_purchased_number;
        this.personalDiscount = personalSale;
        this.programId = programId;
    }

    public Client(Long id, Long coachId, String name, String surname, String login, String password, Integer membership_purchased_number, Float personalSale, Float corporateSale){
        this.id = id;
        this.coachId = coachId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.membership_purchased_number = membership_purchased_number;
        this.personalDiscount = personalSale;

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
                Objects.equals(getProgramId(), client.getProgramId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getCoachId(),getName(),getSurname(),getLogin(),getPassword(),getMembershipPurchasedNumber(),getPersonalDiscount(),getProgramId());
    }

}
