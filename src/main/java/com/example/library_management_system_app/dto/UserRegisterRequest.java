package com.example.library_management_system_app.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String password;
    private String username;
    private String email;
}
