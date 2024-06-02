package com.example.P1.repository;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;
import com.example.P1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * interface contains the implemented methods in JpaRepository
 * needed for the CRUD operations on admin
 */
@Repository
public interface UserConnectionDB extends JpaRepository<User, String> {
    Optional<User> findUserById(String id);

    /**
     *
     * @param id the id of the user
     * @return the result of the sql query to get the latest payment of the user with the id id
     */
    @Query("SELECT bl FROM User u JOIN BillingDetails bl ON u.id = bl.userId WHERE u.id = :id ORDER BY bl.transactionDate DESC LIMIT 1")
    Optional<BillingDetails> getLastPaymentOfUser(String id);

    /**
     *
     * @param userId id of the user
     * @return the result of the sql query to get all the content liked by the user with the id userId
     */
    @Query("SELECT c FROM User u JOIN LikedContent lc ON u.id = lc.userId JOIN Content c ON lc.contentId = c.id WHERE u.id = :userId")
    Optional<List<Content>> getAllLikedContentByUser(String userId);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String s);
}
