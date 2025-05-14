package com.home.finder.homefinder.service.impl;

import com.home.finder.homefinder.entity.House;
import com.home.finder.homefinder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.User;
import com.home.finder.homefinder.exception.UserNotFoundException;
import com.home.finder.homefinder.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User with the userId not found " + id));
        logger.info("user with the email {}", user.getEmail());
        return new UserDto(user.getFullName(), user.getEmail(), user.getPassword());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserDto(user.getFullName(), user.getEmail(), user.getPassword())).collect(Collectors.toList());
    }

    @Override
    public UserDto addUser(User user) {
        User newUser = userRepository.save(user);
        return new UserDto(newUser.getFullName(), newUser.getEmail(), newUser.getPassword());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        logger.info("user with the email {}", email);
        Optional<User> optionalUser  = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDto(user.getFullName(), user.getEmail(), user.getPassword());
        }
        return null;
    }

    @Override
    public void removeHouseFromFavorites(Long userId, House house) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        boolean isHouseInFavorites = user.getFavoriteHouses().stream().anyMatch(h -> h.getHouseId().equals(house.getHouseId()));
        if(!isHouseInFavorites) {
            throw new RuntimeException("House is not find in favorites");
        }

       List<House> houseList = user.getFavoriteHouses().stream().filter(h -> !h.getHouseId().equals(house.getHouseId())).toList();
       user.setFavoriteHouses(houseList);
       userRepository.save(user);
    }
    @Override
    public void addHouseToFavorites(Long userId, House house) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.getFavoriteHouses().add(house);
        userRepository.save(user);
    }
}
