package com.example.library_management_system_app.services;

import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import org.springframework.stereotype.Service;
@Service
public interface BookServices {
    AddBookResponse addBook(BookRequest bookRequest);

    int getNumberOfBooks();
}
