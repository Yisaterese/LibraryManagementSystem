package com.example.library_management_system_app.data.model;

import com.example.library_management_system_app.dto.utility.Response.AuthorResponse;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.cdi.Eager;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Book {
    private String id;
    private String author;
    private String isbn;
    private String title;
    private LocalDate dateAddedToLibrary;
    private boolean isBorrowed;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;
    private BookCategory bookCategory;



}
