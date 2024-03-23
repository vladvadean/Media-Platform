package com.example.P1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BillingDetails {
    @Id
    private String id;
    private String userId;
    private String cardNo;
    private String cvv;
    private String name;

    private LocalDateTime transactionDate;
    public BillingDetails(){

    }
    public BillingDetails(String userId,String cardNo,String cvv,String name,LocalDateTime transactionDate){
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.userId = userId;
        this.name = name;
        this.transactionDate = transactionDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
