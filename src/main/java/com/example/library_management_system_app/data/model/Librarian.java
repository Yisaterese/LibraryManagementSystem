package com.example.library_management_system_app.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Librarian {
    private String id;
    private String username;
    private String email;
    private String password;
}
