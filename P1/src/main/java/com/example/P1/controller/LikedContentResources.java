package com.example.P1.controller;

import com.example.P1.model.Content;
import com.example.P1.model.LikedContent;
import com.example.P1.service.ContentService;
import com.example.P1.service.LikedContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/likedContent")
public class LikedContentResources {
    private final LikedContentService likedContentService;

    public LikedContentResources(LikedContentService likedContentService) {
        this.likedContentService = likedContentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LikedContent>> getAllLikedContent() {
        List<LikedContent> likedContent = likedContentService.getAllLikedContent();
        return new ResponseEntity<>(likedContent, HttpStatus.FOUND);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<LikedContent> getContentById(@PathVariable("id") String id) {
        LikedContent likedContent = likedContentService.getLikedContentById(id);
        return new ResponseEntity<>(likedContent, HttpStatus.FOUND);
    }

    @GetMapping("findUser/{id}")
    public ResponseEntity<List<LikedContent>> getLikedContentByUserId(@PathVariable("id") String id) {
        List<LikedContent> likedContent = likedContentService.getAllLikedContentByUserId(id);
        return new ResponseEntity<>(likedContent, HttpStatus.FOUND);
    }

    @GetMapping("findContent/{id}")
    public ResponseEntity<List<LikedContent>> getLikedContentByContentId(@PathVariable("id") String id) {
        List<LikedContent> likedContent = likedContentService.getAllLikedContentByContentId(id);
        return new ResponseEntity<>(likedContent, HttpStatus.FOUND);
    }

    @GetMapping("findUserContent/{userId}/{contentId}")
    public ResponseEntity<LikedContent> getLikedContentByUserContentId(@PathVariable("userId") String userId,@PathVariable("contentId") String cotentId) {
        LikedContent likedContent = likedContentService.getLikedContentByUserContentId(userId,cotentId);
        return new ResponseEntity<>(likedContent, HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<LikedContent> addLikedContent(@RequestBody LikedContent likedContent) {
        likedContent.setId(UUID.randomUUID().toString());
        LikedContent likedContent1 = likedContentService.addLikedContent(likedContent);

        return new ResponseEntity<>(likedContent1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<LikedContent> updateLikedContent(@RequestBody LikedContent likedContent) {
        LikedContent likedContent1 = likedContentService.updateLikedContent(likedContent);
        return new ResponseEntity<>(likedContent1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLikedContent(@PathVariable("id") String id) {
        likedContentService.deleteUserByLikedContentId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
