package com.example.P1.service;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.model.User;
import com.example.P1.service.notifications.Observable;
import com.example.P1.service.notifications.Observer;
import com.example.P1.repository.UserConnectionDB;
import com.example.P1.contract.UserConnectionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
/**
 * this class implements the CRUD user contract
 * and calls all the methods needed already implemented
 * in the UserConnectionDB interface
 */
@Service
public class UserService implements UserConnectionContract {
    /**
     * interface that contains references to the JPA CRUD operations
     * and used for interface dependency injection
     */
    private final UserConnectionDB userConnectionDB;
    /**
     * observable interface needed to handle the addition/removal of the notified users
     */
    private final Observable contentService;

    @Autowired
    public UserService(UserConnectionDB userConnectionDB, Observable contentService) {
        this.userConnectionDB = userConnectionDB;
        this.contentService = contentService;
    }

    @Override
    public User getUserById(String id) {
        return userConnectionDB.findUserById(id).orElseThrow(() -> new ItemNotFoundException("User by id " + id + " was not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userConnectionDB.findAll();
    }

    @Override
    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        contentService.registerObserver((Observer) user);
        return userConnectionDB.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        User  user = userConnectionDB.findUserById(id).orElse(null);
        if(user != null){
            contentService.removeObserver(user);
            userConnectionDB.deleteById(id);
        }
    }

    @Override
    public User updateUser(User user) {
        return userConnectionDB.save(user);
    }

    /**
     *
     * @param id the id of the user
     * @return the last payment of the user with the id id
     */
    @Override
    public BillingDetails getLastPaymentOfUser(String id) {
        return userConnectionDB.getLastPaymentOfUser(id).orElseThrow(() -> new ItemNotFoundException("User by id " + id + " has no recorded payments"));
    }

    /**
     *
     * @param userId id of the user
     * @return the list of content liked by the user with the id userId
     */
    @Override
    public List<Content> getAllLikedContentByUser(String userId){
        return userConnectionDB.getAllLikedContentByUser(userId).orElseThrow(() -> new ItemNotFoundException("User by id " + userId + " did not leave any like"));
    }
}