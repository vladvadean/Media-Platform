package com.example.P1.service;

import com.example.P1.contract.LikedContentConnectionContract;
import com.example.P1.model.Content;
import com.example.P1.model.ItemNotFoundException;
import com.example.P1.model.LikedContent;
import com.example.P1.repository.LikedContentConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * this class implements the CRUD liked content contract
 * and calls all the methods needed already implemented
 * in the LikedContentConnectionDB interface
 */
@Service
public class LikedContentService implements LikedContentConnectionContract {
    /**
     * interface that contains references to the JPA CRUD operations
     * and used for interface dependency injection
     */
    private final LikedContentConnectionDB likedContentConnectionDB;

    @Autowired
    public LikedContentService(LikedContentConnectionDB likedContentConnectionDB) {
        this.likedContentConnectionDB = likedContentConnectionDB;
    }

    @Override
    public List<LikedContent> getAllLikedContent() {
        return likedContentConnectionDB.findAll();
    }

    /**
     * implement the method that returns all entries of liked content by user id
     * @param userId user id
     * @return a List<LikedContent> or throw a liked content not found exception
     */
    @Override
    public List<LikedContent> getAllLikedContentByUserId(String userId) {
        return likedContentConnectionDB.findLikedContentByUserId(userId).orElseThrow(() -> new ItemNotFoundException("Liked content by user id " + userId + " was not found"));
    }

    /**
     * implement the method that returns all entries of liked content by content id
     * @param contentId content id
     * @return a List<LikedContent> or throw a liked content not found exception
     */
    @Override
    public List<LikedContent> getAllLikedContentByContentId(String contentId) {
        return likedContentConnectionDB.findLikedContentByContentId(contentId).orElseThrow(() -> new ItemNotFoundException("Liked content by content id " + contentId + " was not found"));

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
        return likedContentConnectionDB.findLikedContentById(id).orElseThrow(() -> new ItemNotFoundException("Liked content by id " + id + " was not found"));
    }

    @Override
    public List<Content> getContentByUser(String userId) {
        return likedContentConnectionDB.getContentByUser(userId).orElseThrow
                (() -> new ItemNotFoundException("This user did not like any content"));
    }

    /**
     * implement the method that returns all entries of liked content by user id and content id
     * @param userId user id
     * @param contentId content id
     * @return a LikedContent object or a liked content not found exception
     */
    @Override
    public LikedContent getLikedContentByUserContentId(String userId, String contentId) {
        return likedContentConnectionDB.findLikedContentByUserContentId(userId, contentId).orElseThrow
                (() -> new ItemNotFoundException("Liked content by user id " + userId + " and content id " + contentId + " not found"));
    }
}
