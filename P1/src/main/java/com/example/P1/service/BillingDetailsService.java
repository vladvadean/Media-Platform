package com.example.P1.service;

import com.example.P1.contract.BillingDetailsConnectionContract;
import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;
import com.example.P1.model.ContentNotFoundException;
import com.example.P1.repository.BillingDetailsConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BillingDetailsService implements BillingDetailsConnectionContract {
    private final BillingDetailsConnectionDB billingDetailsConnectionDB;

    @Autowired
    public BillingDetailsService(BillingDetailsConnectionDB billingDetailsConnectionDB) {
        this.billingDetailsConnectionDB = billingDetailsConnectionDB;
    }
    @Override
    public BillingDetails getBillingDetailsById(String id) {
        return billingDetailsConnectionDB.findBillingDetailsByUserId(id).orElseThrow(() -> new ContentNotFoundException("Billing details by user id " + id + " was not found"));
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
