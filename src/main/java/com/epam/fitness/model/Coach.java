package com.epam.fitness.model;

import java.util.Objects;

/**
 * The type Coach.
 */
public class Coach implements Identifiable {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;

    /**
     * Instantiates a new Coach.
     *
     * @param id       the id
     * @param name     the name
     * @param surname  the surname
     * @param login    the login
     * @param password the password
     */
    public Coach(Long id, String name, String surname, String login, String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    /**
     * Instantiates a new Coach.
     */
    public Coach(){}

    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
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
