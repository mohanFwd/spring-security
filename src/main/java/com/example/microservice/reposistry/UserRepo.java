package com.example.microservice.reposistry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.microservice.entity.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);
}