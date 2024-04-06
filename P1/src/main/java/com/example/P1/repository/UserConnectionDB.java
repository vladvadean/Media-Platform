package com.example.P1.repository;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * interface contains the implemented methods in JpaRepository
 * needed for the CRUD operations on admin
 */
@Repository
public interface UserConnectionDB extends JpaRepository<User, String> {
    Optional<User> findUserById(String id);
    @Query("SELECT bl FROM User u JOIN BillingDetails bl ON u.id = bl.userId WHERE u.id = :id ORDER BY bl.transactionDate DESC LIMIT 1")
    Optional<BillingDetails> getLastPaymentOfUser(String id);

}
