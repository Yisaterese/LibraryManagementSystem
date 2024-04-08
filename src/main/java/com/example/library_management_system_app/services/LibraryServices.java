package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LibraryServices {
    RegisterResponse registerUser(RegisterRequest registerRequest);
    RegisterResponse registerLibrarian(RegisterRequest userRegisterRequest);

    int getNumberOfUsers();

    void deleteUserBy(String username);

    List<User> getUsers();

    void deleteByUsername(String username);

    int getNumberOfBooks();

    int getNumberOfLibrarians();

    Book findBookByAuthorAndTitle(Author author,String title);

    Book findBookByAuthorAndTitle(String authorName, String bookTitle);

    AddBookResponse addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest);
}
