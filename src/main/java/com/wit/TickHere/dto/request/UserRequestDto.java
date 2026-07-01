package com.wit.TickHere.dto.request;

import com.wit.TickHere.constant.EUserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
    private Integer roleId;
    private EUserStatus status;
}
