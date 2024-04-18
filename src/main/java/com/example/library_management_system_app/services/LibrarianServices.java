package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.dto.*;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.DeleteBookResponse;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibrarianServices {
    List<Book> getBooksBorrowed();
    RegisterResponse registerLibrarian(RegisterRequest registerRequest);
    int getNumberOfLibrarians();
    Book findBookByIsbn(FindBookRequest findBookRequest);
    void deleteByUsername(String username);
    AddBookResponse addBookToLibrary(BookRequest bookRequest);
    int getNumberOfBooks();
    DeleteBookResponse deleteBookByTitle(DeleteBookRequest deleteBookRequest);

    void setBorrowedBook(String userName);
    Book findBookByAuthorAndTitle(Author author, String title);

    List<Book> returnedBorrowedBooks();
}
