package com.home.finder.homefinder.service.impl;

import com.home.finder.homefinder.entity.RefreshToken;
import com.home.finder.homefinder.repository.RefreshTokenRepository;
import com.home.finder.homefinder.service.JwtService;
import com.home.finder.homefinder.service.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private RefreshTokenRepository refreshTokenRepository;
    private JwtService jwtService;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository,
                                   JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

    @Override
    public RefreshToken getRefreshTokenByToken(String refreshToken) {
        return refreshTokenRepository.getRefreshTokenByToken(refreshToken).orElse(null);
    }

    @Override
    public RefreshToken generateRefreshToken(String email) {
        RefreshToken refreshToken = new RefreshToken(
                jwtService.buildTokenWithEmail(email, new HashMap<>()),
                email,
                LocalDate.now()
        );
        return refreshTokenRepository.saveRefreshToken(refreshToken);
    }

    @Override
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.getRefreshTokenByToken(refreshToken)
                .ifPresentOrElse(
                        rt -> refreshTokenRepository.deleteRefreshToken(refreshToken),
                        () -> System.out.println("Refresh Token with token not found " + refreshToken));
    }
}
