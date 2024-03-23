package com.example.P1.repository;

import com.example.P1.model.Admin;
import com.example.P1.model.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingDetailsConnectionDB extends JpaRepository<BillingDetails,String> {
    Optional<BillingDetails> findBillingDetailsByUserId(String userId);
}
