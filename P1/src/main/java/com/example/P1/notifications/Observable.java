package com.example.P1.notifications;

import com.example.P1.model.Content;

public interface Observable {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(Content content);
}
