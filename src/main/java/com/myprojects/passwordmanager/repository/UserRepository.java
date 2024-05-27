package com.myprojects.passwordmanager.repository;

import com.myprojects.passwordmanager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
