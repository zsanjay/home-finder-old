package com.home.finder.homefinder.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.exception.UserNotFoundException;
import com.home.finder.homefinder.model.LoginRequest;
import com.home.finder.homefinder.model.SignUpRequest;
import com.home.finder.homefinder.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    // private final UserRepository authRepository;

    // public UserService(UserRepository authRepository) {
    //     this.authRepository = authRepository;
    // }

    // public UserDto login(LoginRequest loginRequest) throws UserNotFoundException {
       
    //    Optional<User> userOptional = authRepository.findByEmail(loginRequest.email());
    //    if(userOptional.isPresent()) {
    //     User user = userOptional.get();
    //     return new UserDto(user.getFullName() , user.getEmail(), user.getPassword());
    //    }
    //    throw new UserNotFoundException("No User is found with email " + loginRequest.email());
    // }

    // public UserDto register(SignUpRequest signUpRequest) {

    //     User user = new User();
    //     user.setId(1l);
    //     user.setEmail(signUpRequest.email());
    //     user.setFullName(signUpRequest.fullName());
    //     user.setPassword(signUpRequest.password());

    //     user = authRepository.save(user);
    //     return new UserDto(user.getFullName(), user.getEmail(), user.getPassword());
    // }
}
