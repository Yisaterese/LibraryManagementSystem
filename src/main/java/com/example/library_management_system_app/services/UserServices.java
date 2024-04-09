package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServices {
    int getNumberOfUsers();
    void save(User newUser);
    void removeByUsername(String username);
    List<User> getUser();
    RegisterResponse registerUser(RegisterRequest registerRequest);

    Book userFindBookByAuthorAndTitle(Author author, String title);
    Book userFindBookByAuthorAndTitle(String author, String title);

    Book borrowBook(String title, String username);

    User findUserByUsername(String username);

    void returnBookBorrowed(String title);
}
