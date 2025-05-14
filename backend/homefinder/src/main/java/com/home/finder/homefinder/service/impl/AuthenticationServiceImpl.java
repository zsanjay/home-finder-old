package com.home.finder.homefinder.service.impl;

import com.home.finder.homefinder.dto.LoginResponse;
import com.home.finder.homefinder.entity.RefreshToken;
import com.home.finder.homefinder.model.RefreshRequest;
import com.home.finder.homefinder.service.AuthenticationService;
import com.home.finder.homefinder.service.JwtService;
import com.home.finder.homefinder.service.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.exception.UserNotFoundException;
import com.home.finder.homefinder.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SequenceGeneratorServiceImpl sequenceGeneratorService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(
        UserRepository userRepository, 
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        SequenceGeneratorServiceImpl sequenceGeneratorService,
        RefreshTokenService refreshTokenService,
        JwtService jwtService
        ) {
            this.authenticationManager = authenticationManager;
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.sequenceGeneratorService = sequenceGeneratorService;
            this.refreshTokenService = refreshTokenService;
            this.jwtService = jwtService;
    }

    @Override
    public User signUp(UserDto userDto) {
        User user = new User();
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setFullName(userDto.fullName());
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public LoginResponse authenticate(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password())
        );

        User principal = (User) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtService.generateAccessToken(principal);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(userDto.email());
        User user = userRepository.findByEmail(userDto.email()).orElseThrow(() -> new UserNotFoundException("No User found with the email " + userDto.email()));
        return new LoginResponse(String.valueOf(user.getId()), principal.getUsername(), accessToken, refreshToken.getRefreshToken());
    }

    @Override
    @Transactional
    public void logout(RefreshRequest refreshRequest) {
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenByToken(refreshRequest.refreshToken());
        if(Objects.isNull(refreshToken)) {
            throw new RuntimeException("Refresh Token is not found");
        }
        refreshTokenService.deleteRefreshToken(refreshRequest.refreshToken());
    }

    @Override
    @Transactional
    public LoginResponse refreshToken(RefreshRequest refreshRequest) {
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenByToken(refreshRequest.refreshToken());

        if(Objects.isNull(refreshToken)) {
            throw new RuntimeException("Refresh Token is not found");
        }

        Optional<User> optionalUser = userRepository.findByEmail(refreshRequest.email());
        if(optionalUser.isPresent()) {
            String newToken = jwtService.buildTokenWithEmail(refreshRequest.email(), new HashMap<>());
            return new LoginResponse(String.valueOf(optionalUser.get().getId()), refreshRequest.email(), newToken, refreshRequest.refreshToken());
        }
        return null;
    }
}


