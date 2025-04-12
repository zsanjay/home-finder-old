package com.home.finder.homefinder.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(@NotBlank(message = "Full Name should not be blank") String fullName,
  @Email String email,
  @NotBlank(message = "Password should not be blank") String password) {}
