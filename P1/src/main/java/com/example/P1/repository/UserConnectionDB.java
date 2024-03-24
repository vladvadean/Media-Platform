package com.example.P1.repository;

import com.example.P1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * interface contains the implemented methods in JpaRepository
 * needed for the CRUD operations on admin
 */
@Repository
public interface UserConnectionDB extends JpaRepository<User, String> {
    Optional<User> findUserById(String id);

}
