package com.example.library_management_system_app.dto;

import com.example.library_management_system_app.data.model.Author;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BookRequest {
    private String id;
    private String author;
    private String isbn;
    private String title;
    private String dateAddedToLibrary;
    private boolean isBorrowed;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;
}
