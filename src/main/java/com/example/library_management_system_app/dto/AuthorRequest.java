package com.example.library_management_system_app.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AuthorRequest {
    private String id;
    private String firstname;
    private String lastname;
    private String Autobiography;
    private String dateOfBirth;
    private String Gender;
    private String nationality;
    private String contactInfo;
    private String email;
}
