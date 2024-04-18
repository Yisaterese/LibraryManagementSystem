package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.*;
import com.example.library_management_system_app.dto.utility.Response.*;
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

    Book findBookByIsbn(FindBookRequest findBookRequest);

    Book findBookByAuthorAndTitle(Author author, String title);

    LoginResponse login(LoginRequest loginRequest);

    Book findBookByAuthorAndTitle(String authorName, String bookTitle);

    AddBookResponse addBookToLibrary(BookRequest bookRequest);

    DeleteBookResponse deleteBookByTitle(DeleteBookRequest deletebookRequest);

    void recordBookBorrower(String username);

    List<Book> getBorrowedBooks();

    BorrowBookResponse borrowBook(BorrowBookRequest bookRequest);

    LogoutResponse logout(LogoutRequest logoutRequest);

    List<Book> returnedBorrowedBooks();

    ReturnBorrowedBookResponse returnBorrowedBookResponse(ReturnedBorrowedBookRequest request);
}
