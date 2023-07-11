package com.example.prac230711.repository;

import com.example.prac230711.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUsername (String username);
    Optional<UserEntity> findByUsername(String username);
}
