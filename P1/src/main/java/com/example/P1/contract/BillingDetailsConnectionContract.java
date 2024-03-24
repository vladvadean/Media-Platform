package com.example.P1.contract;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;

import java.util.List;
/**
 * a contract interface to make sure the methods
 * to make CRUD billing details operations are implemented
 */
public interface BillingDetailsConnectionContract {
    public BillingDetails getBillingDetailsById(String id);

    public List<BillingDetails> getAllBillingDetails();

    public BillingDetails addBillingDetails(BillingDetails billingDetails);

    public void deleteBillingDetailsById(String id);

    public BillingDetails updateBillingDetails(BillingDetails billingDetails);
}
