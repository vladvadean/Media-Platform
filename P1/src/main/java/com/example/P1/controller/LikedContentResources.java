package com.example.P1.controller;

import com.example.P1.contract.LikedContentConnectionContract;
import com.example.P1.model.Content;
import com.example.P1.model.LikedContent;
import com.example.P1.service.ContentService;
import com.example.P1.service.LikedContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * communication between the http requests and application
 * responsible for liked content type requests
 */
@RestController
@RequestMapping("/likedContent")
public class LikedContentResources {
    /**
     * class attribute needed for the CRUD methods implementation
     */
    private final LikedContentConnectionContract likedContentService;

    public LikedContentResources(LikedContentConnectionContract likedContentService) {
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

    /**
     * return the result of a SELECT operation by userId
     * @param userId user id
     * @return an http response entity
     */
    @GetMapping("findUser/{userId}")
    public ResponseEntity<List<LikedContent>> getLikedContentByUserId(@PathVariable("userId") String userId) {
        List<LikedContent> likedContent = likedContentService.getAllLikedContentByUserId(userId);
        return new ResponseEntity<>(likedContent, HttpStatus.FOUND);
    }

    /**
     * return the result of a SELECT operation by contentId
     * @param contentId content id
     * @return an http response entity
     */
    @GetMapping("findContent/{contentId}")
    public ResponseEntity<List<LikedContent>> getLikedContentByContentId(@PathVariable("contentId") String contentId) {
        List<LikedContent> likedContent = likedContentService.getAllLikedContentByContentId(contentId);
        return new ResponseEntity<>(likedContent, HttpStatus.FOUND);
    }

    /**
     * return the result of a SELECT operation by userId and contentId
     * @param userId user id
     * @param cotentId content id
     * @return an http response entity
     */
    @GetMapping("findUserContent/{userId}/{contentId}")
    public ResponseEntity<LikedContent> getLikedContentByUserContentId(@PathVariable("userId") String userId, @PathVariable("contentId") String cotentId) {
        LikedContent likedContent = likedContentService.getLikedContentByUserContentId(userId, cotentId);
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
