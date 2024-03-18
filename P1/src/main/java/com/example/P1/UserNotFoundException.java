package com.example.P1;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s){
        super(s);
    }
}