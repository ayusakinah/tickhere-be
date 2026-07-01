package com.wit.TickHere.service.impl;

import com.wit.TickHere.constant.EUserStatus;
import com.wit.TickHere.dto.request.UserRequestDto;
import com.wit.TickHere.dto.response.UserResponseDto;
import com.wit.TickHere.entity.Role;
import com.wit.TickHere.entity.User;
import com.wit.TickHere.exception.BadRequestException;
import com.wit.TickHere.exception.NotFoundException;
import com.wit.TickHere.mapper.UserMapper;
import com.wit.TickHere.repository.RoleRepository;
import com.wit.TickHere.repository.UserRepository;
import com.wit.TickHere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private final UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto createUser(UserRequestDto req) {
        User user = new User();

        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new BadRequestException("Username already used");
        }
        user.setUsername(req.getUsername());

        if(userRepository.findByEmail(req.getEmail()).isPresent()){
            throw new BadRequestException("Email already used");
        }
        user.setEmail(req.getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(req.getPassword()));

        Role role = roleRepository.findById(req.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found"));
        user.setRole(role);

        user.setStatus(EUserStatus.ACTIVE);

        userRepository.save(user);

        // mapping ke reponse ..Cara 1
//        UserResponseDto resp = new UserResponseDto();
//        resp.setUserId(user.getId());
//        resp.setUsername(user.getUsername());
//        resp.setEmail(user.getEmail());
//        resp.setRole(role.getName().name());
//        resp.setStatus(user.getStatus());

        // mapping dengan helper ..Cara 2
        return userMapper.toResponse(user);

//        return resp;
    }

    @Override
    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserResponseDto updateUser(String id, UserRequestDto request) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User with ID not found"));

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        if(request.getRoleId() != null) {
            Role role = roleRepository.findByName(user.getRole().getName())
                    .orElseThrow(() -> new NotFoundException("Role not found"));
            user.setRole(role);
        }

        if(request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setStatus(EUserStatus.DEACTIVE);
        userRepository.save(user);
    }
}
