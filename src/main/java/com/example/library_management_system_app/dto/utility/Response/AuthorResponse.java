package com.example.library_management_system_app.dto.utility.Response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AuthorResponse {
    private String firstname;
    private String lastname;
    private String Autobiography;
    private LocalDate dateOfBirth;
    private String Gender;
    private String nationality;
    private String contactInfo;
    private String email;
}
