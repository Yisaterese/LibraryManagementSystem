package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import org.springframework.stereotype.Service;
@Service
public interface BookServices {
    AddBookResponse addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest);

    int getNumberOfBooks();

    Book findBookByAuthorAndTitle(Author author, String title);
    Book findBookByAuthorAndTitle(String author, String title);

    void deleteBookByTitle(String title);

    Book borrowbook(String title);
}
