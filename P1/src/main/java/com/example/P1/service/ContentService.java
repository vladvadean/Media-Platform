package com.example.P1.service;

import com.example.P1.model.Content;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.model.User;
import com.example.P1.service.notifications.Observable;
import com.example.P1.service.notifications.Observer;
import com.example.P1.repository.ContentConnectionDB;
import com.example.P1.contract.ContentConnectionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * this class implements the CRUD content contract
 * and calls all the methods needed already implemented
 * in the ContentConnectionDB interface
 */
@Service
public class ContentService implements ContentConnectionContract, Observable {
    /**
     * interface that contains references to the JPA CRUD operations
     * and used for interface dependency injection
     */
    private final ContentConnectionDB contentConnectionDB;
    /**
     * list containing all the users that shall be notified
     */
    private List<Observer> observers = new ArrayList<>();

    @Autowired
    public ContentService(ContentConnectionDB contentConnectionDB) {
        this.contentConnectionDB = contentConnectionDB;
    }

    @Override
    public Content getContentById(String id) {
        return contentConnectionDB.findContentById(id).orElseThrow(() -> new ItemNotFoundException("Content by id " + id + " was not found"));
    }

    @Override
    public List<Content> getAllContent() {
        return contentConnectionDB.findAll();
    }

    @Override
    public Content addContent(Content content) {
        content.setId(UUID.randomUUID().toString());
        //notify users of added content
        notifyObservers(content);
        return contentConnectionDB.save(content);
    }

    @Override
    public List<User> getAllUsersThatLiked(String contentId){
        return contentConnectionDB.getAllUserThatLiked(contentId).orElseThrow(() -> new ItemNotFoundException("Content by id " + contentId + " was liked by none"));
    }

    @Override
    public List<Content> getAllContentAdmin(String adminId) {
        return contentConnectionDB.getAllContentAdmin(adminId).orElseThrow(() -> new ItemNotFoundException("Admin by id " + adminId + " did not add anything"));
    }

    @Override
    public void deleteContentById(String id) {
        contentConnectionDB.deleteById(id);
    }

    @Override
    public Content updateContent(Content content) {
        return contentConnectionDB.save(content);
    }

    //notifications logic and observable methods

    /**
     * @param observer the user that needs to be added to the notification list
     */
    @Override
    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    /**
     * @param observer the user that needs to be removed from the notification list
     */
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    /**
     * @param content the content entity that is added and that triggers the notification of the users
     */
    @Override
    public void notifyObservers(Content content) {
        for(Observer observer:observers){
            observer.update(content);
        }
    }
}
