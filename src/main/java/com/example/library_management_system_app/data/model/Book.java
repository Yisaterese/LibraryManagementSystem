package com.example.library_management_system_app.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Book {
    private String id;
    private Author author;
    private String isbn;
    private String title;
    private LocalDate publishedDate;
}
