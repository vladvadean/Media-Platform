package com.example.P1.repository;

import com.example.P1.model.Content;
import com.example.P1.model.LikedContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * interface contains the implemented methods in JpaRepository
 * needed for the CRUD operations on admin
 */
@Repository
public interface LikedContentConnectionDB extends JpaRepository<LikedContent, String> {
    /**
     * method that returns the entry with a certain user id and content id
     * requires a custom query defined below, otherwise unhandled exception
     * @param userId user id
     * @param contentId content id
     * @return optional a LikedContent object
     */
    @Query("SELECT lc FROM LikedContent lc WHERE lc.userId= :userId AND lc.contentId = :contentId")
    Optional<LikedContent>findLikedContentByUserContentId(String userId,String contentId);
    Optional<LikedContent>findLikedContentById(String id);
    Optional<List<LikedContent>>findLikedContentByUserId(String userId);

    Optional<List<LikedContent>>findLikedContentByContentId(String contentId);

    @Query("SELECT c FROM LikedContent lc JOIN Content c ON c.id = lc.contentId WHERE lc.userId = :userId")
    Optional<List<Content>>getContentByUser(String userId);

}
