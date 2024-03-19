package com.example.P1.service;

import com.example.P1.model.Admin;
import com.example.P1.model.AdminNotFoundException;
import com.example.P1.model.Content;
import com.example.P1.model.ContentNotFoundException;
import com.example.P1.repository.AdminConnection;
import com.example.P1.repository.ContentConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContentService {
    private final ContentConnection contentConnection;

    @Autowired
    public ContentService(ContentConnection contentConnection) {
        this.contentConnection = contentConnection;
    }

    public Content getContentById(String id) {
        return contentConnection.findContentById(id).orElseThrow(() -> new ContentNotFoundException("Admin by id " + id + " was not found"));
    }

    public List<Content> getAllContent() {
        return contentConnection.findAll();
    }

    public Content addContent(Content content) {
        content.setId(UUID.randomUUID().toString());
        return contentConnection.save(content);
    }

    public void deleteContentById(String id) {
        contentConnection.deleteById(id);
    }

    public Content updateContent(Content content) {
        return contentConnection.save(content);
    }
}
