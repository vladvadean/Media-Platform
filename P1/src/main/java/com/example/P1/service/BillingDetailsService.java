package com.example.P1.service;

import com.example.P1.contract.BillingDetailsConnectionContract;
import com.example.P1.model.BillingDetails;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.repository.BillingDetailsConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * this class implements the CRUD billing details contract
 * and calls all the methods needed already implemented
 * in the BillingDetailsConnectionDB interface
 */
@Service
public class BillingDetailsService implements BillingDetailsConnectionContract {
    /**
     * interface that contains references to the JPA CRUD operations
     * and used for interface dependency injection
     */
    private final BillingDetailsConnectionDB billingDetailsConnectionDB;

    @Autowired
    public BillingDetailsService(BillingDetailsConnectionDB billingDetailsConnectionDB) {
        this.billingDetailsConnectionDB = billingDetailsConnectionDB;
    }
    @Override
    public List<BillingDetails> getBillingDetailsById(String id) {
        return billingDetailsConnectionDB.findBillingDetailsByUserId(id).orElseThrow(() -> new ItemNotFoundException("Billing details by user id " + id + " was not found"));
    }

    @Override
    public List<BillingDetails> getAllBillingDetails() {
        return billingDetailsConnectionDB.findAll();
    }

    @Override
    public BillingDetails addBillingDetails(BillingDetails billingDetails) {
        return billingDetailsConnectionDB.save(billingDetails);
    }

    @Override
    public void deleteBillingDetailsById(String userId) {
        billingDetailsConnectionDB.deleteById(userId);
    }

    @Override
    public BillingDetails updateBillingDetails(BillingDetails billingDetails) {
        return billingDetailsConnectionDB.save(billingDetails);
    }

}
