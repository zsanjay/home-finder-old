package com.home.finder.homefinder.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class RefreshToken implements Serializable {
    private static final long serialVersionUID = 1L;
    public String refreshToken;
    public String email;
    public LocalDate createdAt;
    public RefreshToken() {}
    public RefreshToken(String refreshToken, String email, LocalDate createdAt) {
        this.refreshToken = refreshToken;
        this.email = email;
        this.createdAt = createdAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
