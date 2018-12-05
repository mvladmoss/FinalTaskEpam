package com.epam.fitness.model;

import java.io.Serializable;

public class Client implements Serializable {

    private Long id;
    private Integer coachId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Integer visitNumber;
    private Float sale;
    private Boolean isCorporate;
    private Program program;

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
     }

    public void setID(long clientID) {
        this.id = clientID;
    }

    public void setCoachId(int coachId) {
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

    public void setSale(float sale) {
        this.sale = sale;
    }

    public void setCorporate(boolean corporate) {
        isCorporate = corporate;
    }

    public long getId() {
        return id;
    }

    public int getCoachId() {
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

    public float getSale() {
        return sale;
    }
    public boolean isCorporate() {
        return isCorporate;
    }

    public String getName() {
        return name;
    }



}
