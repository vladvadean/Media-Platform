package com.example.P1.model;

import jakarta.persistence.*;

/**
 * entity class for admin containing:
 * id, name, username and password
 * entity class used to store all the authentication
 * data for admins
 */
@Entity
public class Admin {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;

    public Admin(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;

    }

    public Admin() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
