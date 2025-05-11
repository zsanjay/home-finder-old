package com.home.finder.homefinder.controller;

import com.home.finder.homefinder.model.RefreshRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.finder.homefinder.dto.LoginResponse;
import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.service.AuthenticationService;
import com.home.finder.homefinder.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User registeredUser = authenticationService.signUp(userDto);
        return ResponseEntity.ok(registeredUser);
    } 

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.authenticate(userDto));
    }
    @PostMapping("/logout")
    public String logout(@RequestBody RefreshRequest refreshRequest) {
        authenticationService.logout(refreshRequest);
        return "LoggedOut";
    }
}
