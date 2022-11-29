package com.lukyanov.authservice.repositories;

import com.lukyanov.authservice.entities.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String login);
}
