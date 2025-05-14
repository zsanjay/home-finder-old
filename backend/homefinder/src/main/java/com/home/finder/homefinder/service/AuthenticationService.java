package com.home.finder.homefinder.service;

import com.home.finder.homefinder.dto.LoginResponse;
import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.model.RefreshRequest;

public interface AuthenticationService {
    User signUp(UserDto userDto);
    LoginResponse authenticate(UserDto userDto);
    void logout(RefreshRequest refreshRequest);
    LoginResponse refreshToken(RefreshRequest refreshRequest);
}
