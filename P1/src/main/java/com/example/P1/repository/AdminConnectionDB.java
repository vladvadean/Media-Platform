package com.example.P1.repository;

import com.example.P1.model.Admin;
import com.example.P1.model.Content;
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
public interface AdminConnectionDB extends JpaRepository<Admin, String> {
    Optional<Admin> findAdminById(String id);

    /**
     *
     * @param adminId id of the admin
     * @return the result of the sql query to get all content added by an admin with the id adminId
     */
    @Query("SELECT c FROM Admin a JOIN Content c ON a.id = c.adminId WHERE a.id = :adminId")
    Optional<List<Content>> getAllContentByAdminId(String adminId);
}
