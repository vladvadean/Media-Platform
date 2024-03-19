package com.example.P1.service;

import com.example.P1.model.UserNotFoundException;
import com.example.P1.model.User;
import com.example.P1.repository.UserConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserConnection userConnection;

    @Autowired
    public UserService(UserConnection userConnection) {
        this.userConnection = userConnection;
    }

    public User getUserById(String id) {
        return userConnection.findUserById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public List<User> getAllUsers() {
        return userConnection.findAll();
    }

    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return userConnection.save(user);
    }

    public void deleteUserById(String id) {
        userConnection.deleteById(id);
    }

    public User updateUser(User user) {
        return userConnection.save(user);
    }
}