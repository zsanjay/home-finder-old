package com.home.finder.homefinder.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.exception.UserNotFoundException;
import com.home.finder.homefinder.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SequenceGeneratorService sequenceGeneratorService;

    public AuthenticationService(
        UserRepository userRepository, 
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        SequenceGeneratorService sequenceGeneratorService
        ) {
            this.authenticationManager = authenticationManager;
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public User signUp(UserDto userDto) {
        User user = new User();
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setFullName(userDto.fullName());
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));

        return userRepository.save(user);
    }

    public User authenticate(UserDto userDto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password())
        );
    
        return userRepository.findByEmail(userDto.email()).orElseThrow(() -> new UserNotFoundException("No User found with the email " + userDto.email()));
    }
}


