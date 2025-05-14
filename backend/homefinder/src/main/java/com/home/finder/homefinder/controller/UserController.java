package com.home.finder.homefinder.controller;

import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.House;
import com.home.finder.homefinder.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private UserServiceImpl userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long userId) {
        return userService.getUser(userId);
    }
    @GetMapping("/{email}")
    public UserDto getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/{userId}/addFavorite")
    public void addHouseToFavorites(@PathVariable Long userId, @RequestBody House house) {
        userService.addHouseToFavorites(userId, house);
    }

    @PostMapping("/{userId}/removeFavorite")
    public void removeHouseFromFavorites(@PathVariable Long userId, @RequestBody House house) {
        userService.removeHouseFromFavorites(userId, house);
    }
}
