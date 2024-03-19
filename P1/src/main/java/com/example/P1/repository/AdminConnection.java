package com.example.P1.repository;

import com.example.P1.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminConnection extends JpaRepository<Admin, String> {
    Optional<Admin> findAdminById(String id);
}
