package com.example.library_management_system_app.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class Library {
    private String id;
    private List<Book> books;
    private List<User> users;
}
