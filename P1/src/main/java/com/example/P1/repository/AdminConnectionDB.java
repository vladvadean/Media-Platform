package com.example.P1.repository;

import com.example.P1.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * interface contains the implemented methods in JpaRepository
 * needed for the CRUD operations on admin
 */
@Repository
public interface AdminConnectionDB extends JpaRepository<Admin, String> {
    Optional<Admin> findAdminById(String id);
}
