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

@RestController
@RequestMapping("/content")
public class ContentResources {
    private final ContentService contentService;

    public ContentResources(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Content>> getAllUsers() {
        List<Content> content = contentService.getAllContent();
        return new ResponseEntity<>(content, HttpStatus.FOUND);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Content> getUserById(@PathVariable("id") String id) {
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
    public ResponseEntity<Content> updateUser(@RequestBody Content content) {
        Content content1 = contentService.updateContent(content);
        return new ResponseEntity<>(content1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id) {
        contentService.deleteContentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
