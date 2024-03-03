package com.zuluco.oculuz.model.dtos;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String passwordConfirm;
}
