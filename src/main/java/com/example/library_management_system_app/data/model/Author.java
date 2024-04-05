package com.example.library_management_system_app.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@Document
public class Author {
    private String id;
    private String firstname;
    private String lastname;
    private String biography;
    private LocalDate dateOfBirth;
    private String Gender;
    private String nationality;
    private String contactInfo;
}
