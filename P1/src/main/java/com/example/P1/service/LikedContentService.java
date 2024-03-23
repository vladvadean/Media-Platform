package com.example.P1.service;

import com.example.P1.contract.LikedContentConnectionContract;
import com.example.P1.model.ContentNotFoundException;
import com.example.P1.model.LikedContent;
import com.example.P1.model.LikedContentNotFoundException;
import com.example.P1.repository.LikedContentConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikedContentService implements LikedContentConnectionContract {
    private final LikedContentConnectionDB likedContentConnectionDB;

    @Autowired
    public LikedContentService(LikedContentConnectionDB likedContentConnectionDB) {
        this.likedContentConnectionDB = likedContentConnectionDB;
    }

    @Override
    public List<LikedContent> getAllLikedContent() {
        return likedContentConnectionDB.findAll();
    }

    @Override
    public List<LikedContent> getAllLikedContentByUserId(String userId) {
        return likedContentConnectionDB.findLikedContentByUserId(userId).orElseThrow(() -> new ContentNotFoundException("Liked content by user id " + userId + " was not found"));
    }

    @Override
    public List<LikedContent> getAllLikedContentByContentId(String contentId) {
        return likedContentConnectionDB.findLikedContentByContentId(contentId).orElseThrow(() -> new ContentNotFoundException("Liked content by content id " + contentId + " was not found"));

    }

    @Override
    public LikedContent addLikedContent(LikedContent likedContent) {
        return likedContentConnectionDB.save(likedContent);
    }

    @Override
    public void deleteUserByLikedContentId(String id) {
        likedContentConnectionDB.deleteById(id);
    }

    @Override
    public LikedContent updateLikedContent(LikedContent likedContent) {
        return likedContentConnectionDB.save(likedContent);
    }

    @Override
    public LikedContent getLikedContentById(String id) {
        return likedContentConnectionDB.findLikedContentById(id).orElseThrow(() -> new ContentNotFoundException("Liked content by id " + id + " was not found"));
    }

    @Override
    public LikedContent getLikedContentByUserContentId(String userId, String contentId) {
        return likedContentConnectionDB.findLikedContentByUserContentId(userId, contentId).orElseThrow
                (() -> new LikedContentNotFoundException("Liked content by user id " + userId + " and content id " + contentId));
    }
}
