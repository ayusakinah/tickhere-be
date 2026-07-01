package com.wit.TickHere.service;

import com.wit.TickHere.dto.request.UserRequestDto;
import com.wit.TickHere.dto.response.UserResponseDto;
import com.wit.TickHere.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    UserResponseDto createUser(UserRequestDto user);
    UserResponseDto getUserById(String id);
    Optional<User> getUserByEmail(String email);
    UserResponseDto updateUser(String id, UserRequestDto user);
    void deleteUser(String id);
}
