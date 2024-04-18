package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.DeleteBookRequest;
import com.example.library_management_system_app.dto.utility.Response.DeleteBookResponse;
import com.example.library_management_system_app.dto.utility.Response.UpdateBookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookServices {

    Book returnBookBorrowed(Book bookTitle);

    Author findAuthorBy(Book book);
    void save(Book book);
    UpdateBookResponse updateBookStatus(Book bookStatus);
    Book createBook(BookRequest bookRequest);
    int getNumberOfBooks();
    Book findBookByAuthorAndTitle(Author author, String title);
    Book findBookByAuthorAndTitle(String author, String title);
    DeleteBookResponse deleteBookByTitle(DeleteBookRequest deleteBookRequest);
    Book findBookByTitle(String lowerCase);
    List<Book> getBorrowedBooks();
    Book findBookByIsbn(String isbn);
}
