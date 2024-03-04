package com.zuluco.oculuz.model.dtos.user;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;

@Data
public class UserAccountMainPageDTO {
    private Long id;
    private String username;
    private String password;
    private String accessPermission;
    private Boolean banned;
    private Date banEndDate;
    private Date birthDate;
    private Date registDate;
    private String avatarUrl;
    private String country;
    private ArrayList<String> roles;
}