package com.example.P1.repository;

import com.example.P1.model.Content;
import com.example.P1.model.User;
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
public interface ContentConnectionDB extends JpaRepository<Content, String> {
    Optional<Content> findContentById(String id);

    /**
     *
     * @param contentId id of the content
     * @return the result of the sql query to get all users that liked the content with the id contentId
     */
    @Query("SELECT u FROM  Content c JOIN LikedContent lc ON c.id=lc.contentId JOIN User u ON u.id = lc.userId WHERE c.id = :contentId")
    Optional<List<User>> getAllUserThatLiked(String contentId);
}
