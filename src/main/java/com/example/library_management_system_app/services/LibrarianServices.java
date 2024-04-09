package com.example.library_management_system_app.services;

import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;
@Service
public interface LibrarianServices {
    RegisterResponse registerLibrarian(RegisterRequest registerRequest);

    int getNumberOfLibrarians();

    void deleteByUsername(String username);

   AddBookResponse addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest);

    int getNumberOfBooks();

    void deleteBookByTitle(String title);

    void recordBookBorrower(String userName);
}
