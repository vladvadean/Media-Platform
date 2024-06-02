package com.example.P1.contract;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;
import com.example.P1.model.User;

import java.util.List;

/**
 * a contract interface to make sure the methods
 * to make CRUD user operations are implemented
 */
public interface UserConnectionContract {
    public User getUserById(String id);

    public List<User> getAllUsers();

    public User addUser(User user);

    public void deleteUserById(String id);

    public User updateUser(User user);

    public BillingDetails getLastPaymentOfUser(String id);

    /**
     * @param userId id of the user
     * @return get all content that was liked by user that has the id userId
     */
    public List<Content> getAllLikedContentByUser(String userId);

    public User findByEmailAndPassword(String email, String password);

    public User findUserByEmail(String email);
}
