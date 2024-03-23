package com.example.P1.service;

import com.example.P1.model.Content;
import com.example.P1.model.ContentNotFoundException;
import com.example.P1.repository.ContentConnectionDB;
import com.example.P1.contract.ContentConnectionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContentService implements ContentConnectionContract {
    private final ContentConnectionDB contentConnectionDB;

    @Autowired
    public ContentService(ContentConnectionDB contentConnectionDB) {
        this.contentConnectionDB = contentConnectionDB;
    }

    @Override
    public Content getContentById(String id) {
        return contentConnectionDB.findContentById(id).orElseThrow(() -> new ContentNotFoundException("Admin by id " + id + " was not found"));
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
