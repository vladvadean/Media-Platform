package com.example.P1.repository;

import com.example.P1.model.LikedContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedContentConnectionDB extends JpaRepository<LikedContent, String> {
    @Query("SELECT lc FROM LikedContent lc WHERE lc.userId= :userId AND lc.contentId = :contentId")
    Optional<LikedContent>findLikedContentByUserContentId(String userId,String contentId);
    Optional<LikedContent>findLikedContentById(String id);
    Optional<List<LikedContent>>findLikedContentByUserId(String userId);

    Optional<List<LikedContent>>findLikedContentByContentId(String contentId);

}
