package com.example.P1.controller;

import com.example.P1.model.BillingDetails;
import com.example.P1.service.BillingDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
/**
 * communication between the http requests and application
 * responsible for billing details type requests
 */
@RestController
@RequestMapping("/billingDetails")
public class BillingDetailsResources {
    /**
     * class attribute needed for the CRUD methods implementation
     */
    private final BillingDetailsService billingDetailsService;

    public BillingDetailsResources(BillingDetailsService billingDetailsService) {
        this.billingDetailsService = billingDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BillingDetails>> getAllBillingDetails() {
        List<BillingDetails> billingDetails = billingDetailsService.getAllBillingDetails();
        return new ResponseEntity<>(billingDetails, HttpStatus.FOUND);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<BillingDetails> getBillingDetails(@PathVariable("id") String id) {
        BillingDetails billingDetails = billingDetailsService.getBillingDetailsById(id);
        return new ResponseEntity<>(billingDetails, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<BillingDetails> addBillingDetails(@RequestBody BillingDetails billingDetails) {
        billingDetails.setId(UUID.randomUUID().toString());
        BillingDetails billingDetails1 = billingDetailsService.addBillingDetails(billingDetails);

        return new ResponseEntity<>(billingDetails1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BillingDetails> updateContent(@RequestBody BillingDetails billingDetails) {
        BillingDetails billingDetails1 = billingDetailsService.updateBillingDetails(billingDetails);
        return new ResponseEntity<>(billingDetails1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBillingDetailsById(@PathVariable("id") String id) {
        billingDetailsService.deleteBillingDetailsById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
