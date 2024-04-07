package com.example.P1.notifications;

import com.example.P1.model.Content;

/**
 * interface used to add/remove/notify the users of any change in the content entity implemented in the ContentService
 */
public interface Observable {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(Content content);
}
