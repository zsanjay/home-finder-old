package com.home.finder.homefinder.dto;

public record LoginResponse (
        String userId,
        String email,
        String accessToken,
        String refreshToken
) { }
