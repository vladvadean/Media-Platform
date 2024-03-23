package com.example.P1.contract;

import com.example.P1.model.BillingDetails;
import com.example.P1.model.Content;

import java.util.List;

public interface BillingDetailsConnectionContract {
    public BillingDetails getBillingDetailsById(String userId);

    public List<BillingDetails> getAllBillingDetails();

    public BillingDetails addBillingDetails(BillingDetails billingDetails);

    public void deleteBillingDetailsById(String userId);

    public BillingDetails updateBillingDetails(BillingDetails billingDetails);
}
