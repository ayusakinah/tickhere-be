package com.wit.TickHere.dto.response;

import com.wit.TickHere.constant.EUserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String userId;
    private String username;
    private String email;
    private String role;
    private EUserStatus status;
}
