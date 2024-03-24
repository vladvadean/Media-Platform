package com.example.P1.repository;

import com.example.P1.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * interface contains the implemented methods in JpaRepository
 * needed for the CRUD operations on admin
 */
@Repository
public interface ContentConnectionDB extends JpaRepository<Content, String> {
    Optional<Content> findContentById(String id);
}
