package com.example.P1.repository;

import com.example.P1.model.User;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserConnection extends JpaRepository<User, String> {
    Optional<User> findUserById(String id);

}
