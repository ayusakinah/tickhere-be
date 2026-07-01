package com.wit.TickHere.mapper;

import com.wit.TickHere.dto.response.UserResponseDto;
import com.wit.TickHere.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toResponse(User user) {
        UserResponseDto resp = new UserResponseDto();
        resp.setUserId(user.getId());
        resp.setUsername(user.getUsername());
        resp.setEmail(user.getEmail());
        resp.setRole(user.getRole().getName().name());
        resp.setStatus(user.getStatus());

        return resp;
    }
}
