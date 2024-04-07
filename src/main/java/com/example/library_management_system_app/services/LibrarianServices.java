package com.example.library_management_system_app.services;

import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;
@Service
public interface LibrarianServices {
    RegisterResponse registerLibrarian(RegisterRequest registerRequest);

    int getNumberOfLibrarians();

    void deleteByUsername(String username);

    void addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest);

    int getNumberOfBooks();
}
