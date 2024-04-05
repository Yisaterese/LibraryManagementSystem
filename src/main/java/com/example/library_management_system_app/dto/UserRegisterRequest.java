package com.example.library_management_system_app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterRequest {
    private String id;
    private String username;
    private String email;
    private String password;
   // private String dateOfBirth;
}
