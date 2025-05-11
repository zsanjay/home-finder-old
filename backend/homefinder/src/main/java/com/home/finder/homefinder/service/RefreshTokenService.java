package com.home.finder.homefinder.service;

import com.home.finder.homefinder.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken getRefreshTokenByToken(String refreshToken);
    RefreshToken generateRefreshToken(String email);
    void deleteRefreshToken(String refreshToken);
}