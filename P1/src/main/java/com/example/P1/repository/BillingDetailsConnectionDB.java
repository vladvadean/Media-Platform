package com.example.P1.repository;

import com.example.P1.model.Admin;
import com.example.P1.model.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * interface contains the implemented methods in JpaRepository
 * needed for the CRUD operations on admin
 */
@Repository
public interface BillingDetailsConnectionDB extends JpaRepository<BillingDetails,String> {
    Optional<List<BillingDetails>> findBillingDetailsByUserId(String userId);
}
