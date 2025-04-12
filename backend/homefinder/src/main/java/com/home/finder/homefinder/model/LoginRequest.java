package com.home.finder.homefinder.model;

import jakarta.validation.constraints.Email;

public record LoginRequest(
    @Email(message = "Email should be valid") String email, 
    String password,
    boolean rememberMe
) {}
