package com.example.P1.model;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Date lastPaymentDate;

    public User(String name,String username,String password,String email,Date lastPaymentDate) {
        name = name;
        username = username;
        password = password;
        email = email;
        lastPaymentDate = lastPaymentDate;

    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastPaymentDate=" + lastPaymentDate +
                '}';
    }
}
