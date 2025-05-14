package com.home.finder.homefinder.service;

import com.home.finder.homefinder.dto.UserDto;
import com.home.finder.homefinder.entity.House;
import com.home.finder.homefinder.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto addUser(User user);
    UserDto getUserByEmail(String email);
    UserDto getUser(Long id);
    void addHouseToFavorites(Long userId, House house);
    void removeHouseFromFavorites(Long userId, House house);
}
