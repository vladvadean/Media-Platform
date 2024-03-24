package com.example.P1.service;

import com.example.P1.model.Content;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.repository.ContentConnectionDB;
import com.example.P1.contract.ContentConnectionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
/**
 * this class implements the CRUD content contract
 * and calls all the methods needed already implemented
 * in the ContentConnectionDB interface
 */
@Service
public class ContentService implements ContentConnectionContract {
    /**
     * interface that contains references to the JPA CRUD operations
     * and used for interface dependency injection
     */
    private final ContentConnectionDB contentConnectionDB;

    @Autowired
    public ContentService(ContentConnectionDB contentConnectionDB) {
        this.contentConnectionDB = contentConnectionDB;
    }

    @Override
    public Content getContentById(String id) {
        return contentConnectionDB.findContentById(id).orElseThrow(() -> new ItemNotFoundException("Admin by id " + id + " was not found"));
    }

    @Override
    public List<Content> getAllContent() {
        return contentConnectionDB.findAll();
    }

    @Override
    public Content addContent(Content content) {
        content.setId(UUID.randomUUID().toString());
        return contentConnectionDB.save(content);
    }

    @Override
    public void deleteContentById(String id) {
        contentConnectionDB.deleteById(id);
    }

    @Override
    public Content updateContent(Content content) {
        return contentConnectionDB.save(content);
    }
}
