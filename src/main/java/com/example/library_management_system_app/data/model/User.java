package com.example.library_management_system_app.data.model;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class User {
    private String id;
    private String userName;
    private String email;
    private String password;
    private boolean borrowBook;
    private LocalDate dateBorrowed;
    private LocalDate returnedDate;
    private boolean isLogin;
    @DBRef
    @Lazy
    private List<Book> bookBorrowed = new ArrayList<>();
}
