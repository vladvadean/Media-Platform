package com.example.P1.model;

import com.example.P1.notifications.Observer;
import jakarta.persistence.*;

import java.sql.Date;

/**
 * entity class for user containing:
 * id, username, password, email and last payment date
 * entity to store all the required data for authentication
 * and last payment date
 */
@Entity
public class User implements Observer {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private Date lastPaymentDate;

    public User(String username, String password, String email, Date lastPaymentDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastPaymentDate = lastPaymentDate;

    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void update(Content content) {
        System.out.println("UPDATE: New content added: " + content + " for the user with id: " + id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastPaymentDate=" + lastPaymentDate +
                '}';
    }
}
