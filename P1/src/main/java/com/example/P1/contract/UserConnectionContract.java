package com.example.P1.contract;

import com.example.P1.model.User;

import java.util.List;

/**
 * a contract interface to make sure the methods to make CRUD user operations are implemented
 */
public interface UserConnectionContract {
    public User getUserById(String id);

    public List<User> getAllUsers();

    public User addUser(User user);

    public void deleteUserById(String id);

    public User updateUser(User user);
}
