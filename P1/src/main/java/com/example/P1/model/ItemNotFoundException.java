package com.example.P1.model;

/**
 * exception used for not found result
 * on get requests for admin
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String s) {
        super(s);
    }
}
