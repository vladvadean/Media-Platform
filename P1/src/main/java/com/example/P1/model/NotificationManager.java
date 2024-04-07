package com.example.P1.model;

import com.example.P1.notifications.Observable;
import com.example.P1.notifications.Observer;

import java.util.HashSet;
import java.util.Set;

public class NotificationManager {
    private final Set<Observer> observers = new HashSet<>();
    private final Observable contentService;

    public NotificationManager(Observable contentService) {
        this.contentService = contentService;
    }
    public void subscribe(Observer observer){
        if(observers.add(observer)){
            contentService.registerObserver(observer);
        }
    }

    public void unsubscribe(Observer observer){
        if(observers.remove(observer)){
            contentService.removeObserver(observer);
        }
    }
}
