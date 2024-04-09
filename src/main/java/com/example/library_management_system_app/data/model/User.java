package com.example.library_management_system_app.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class User {
    private String id;
    private String userName;
    private String email;
    private String password;
    private  LocalDate dateOfBirth;
    private boolean hasBorrowed;
}
