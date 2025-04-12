package com.home.finder.homefinder.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.exception.UserNotFoundException;
import com.home.finder.homefinder.model.LoginRequest;
import com.home.finder.homefinder.model.SignUpRequest;
import com.home.finder.homefinder.repository.AuthRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean login(LoginRequest loginRequest) throws UserNotFoundException {

       User user = authRepository.findByEmail(loginRequest.email());

       if(Objects.isNull(user)) {
         throw new UserNotFoundException("No User is found with email " + loginRequest.email());
       }
       return true;
    }

    public void register(SignUpRequest signUpRequest) {

        User user = new User();
        user.setId(1l);
        user.setEmail(signUpRequest.email());
        user.setFullName(signUpRequest.fullName());
        user.setPassword(signUpRequest.password());

        authRepository.save(user);
    }
}
