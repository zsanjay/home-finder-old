package com.home.finder.homefinder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.finder.homefinder.entity.House;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.exception.UserNotFoundException;
import com.home.finder.homefinder.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User with the userId not found " + id));
        logger.info("user with the email {}", user.getEmail());
        return new UserDto(user.getFullName(), user.getEmail(), user.getPassword());
    }

    public UserDto getUserByEmail(String email) {
        logger.info("user with the email {}", email);
        Optional<User> optionalUser  = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDto(user.getFullName(), user.getEmail(), user.getPassword());
        }
        return null;
    }

    public void removeHouseFromFavorites(Long userId, House house) {

    }

    public void addHouseToFavorites(Long userId, House house) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));


    }
}
