package com.example.library_management_system_app.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document
public class Library {
    private String id;
    private List<Book> books;
    private List<User> users;
}
