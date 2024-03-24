package com.example.P1.controller;

import com.example.P1.model.Content;
import com.example.P1.model.User;
import com.example.P1.service.ContentService;
import com.example.P1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * communication between the http requests and application
 * responsible for content type requests
 */
@RestController
@RequestMapping("/content")
public class ContentResources {
    /**
     * class attribute needed for the CRUD methods implementation
     */
    private final ContentService contentService;

    public ContentResources(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Content>> getAllContent() {
        List<Content> content = contentService.getAllContent();
        return new ResponseEntity<>(content, HttpStatus.FOUND);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable("id") String id) {
        Content content = contentService.getContentById(id);
        return new ResponseEntity<>(content, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Content> addContent(@RequestBody Content content) {
        content.setId(UUID.randomUUID().toString());
        Content content1 = contentService.addContent(content);
        
        return new ResponseEntity<>(content1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Content> updateContent(@RequestBody Content content) {
        Content content1 = contentService.updateContent(content);
        return new ResponseEntity<>(content1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContentById(@PathVariable("id") String id) {
        contentService.deleteContentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
