package com.home.finder.homefinder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.home.finder.homefinder.entity.User;

public interface AuthRepository extends MongoRepository<User, Long>{
    
    User findByEmail(String email);
}
