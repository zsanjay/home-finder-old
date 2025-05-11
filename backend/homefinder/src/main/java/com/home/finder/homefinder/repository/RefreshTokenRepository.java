package com.home.finder.homefinder.repository;

import com.home.finder.homefinder.entity.RefreshToken;
import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> getRefreshTokenByToken(String refreshToken);
    RefreshToken saveRefreshToken(RefreshToken refreshToken);
    void deleteRefreshToken(String refreshToken);
}
