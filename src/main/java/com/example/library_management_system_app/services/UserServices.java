package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.BorrowBookResponse;
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

    void recordBookBorrower(String username);

    BorrowBookResponse borrowBook(RegisterRequest registerRequest, BookRequest bookRequest);

    User findUserByUsername(String username);
    Book returnBookBorrowed(String bookTitle);
}
