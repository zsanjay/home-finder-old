package com.home.finder.homefinder.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.home.finder.homefinder.entity.User;

public interface UserRepository extends MongoRepository<User, Long>{
    
    Optional<User> findByEmail(String email);
}
