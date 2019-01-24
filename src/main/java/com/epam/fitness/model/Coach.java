package com.epam.fitness.model;

import java.util.Objects;

public class Coach implements Identifiable {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;

    public Coach(Long id, String name, String surname, String login, String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Coach(){}

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coach coach = (Coach) o;
        return  Objects.equals(getId(), coach.getId()) &&
                Objects.equals(getName(), coach.getName()) &&
                Objects.equals(getSurname(), coach.getSurname()) &&
                Objects.equals(getLogin(), coach.getLogin()) &&
                Objects.equals(getPassword(), coach.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getName(),getSurname(),getLogin(),getPassword());
    }


}
