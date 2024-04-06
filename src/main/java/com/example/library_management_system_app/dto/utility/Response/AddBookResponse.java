package com.example.library_management_system_app.dto.utility.Response;

import com.example.library_management_system_app.data.model.Author;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AddBookResponse {
    private String id;
    private Author author;
    private String isbn;
    private String title;
    private LocalDate dateAddedToLibrary;
}
