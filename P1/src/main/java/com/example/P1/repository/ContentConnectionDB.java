package com.example.P1.repository;

import com.example.P1.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentConnectionDB extends JpaRepository<Content, String> {
    Optional<Content> findContentById(String id);
}
