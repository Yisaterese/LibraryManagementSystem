package com.example.library_management_system_app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String id;
    private String username;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private LocalDate dateBorrowed;
}

