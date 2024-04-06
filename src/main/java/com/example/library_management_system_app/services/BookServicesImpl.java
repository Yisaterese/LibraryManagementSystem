package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.library_management_system_app.dto.utility.Mapper.mapBook;

@Service
public class BookServicesImpl implements BookServices {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public AddBookResponse addBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());
        book.setDateAddedToLibrary(bookRequest.getDateAddedToLibrary());
        book.setId(book.getId());
        bookRepository.save(book);
        return mapBook(book);
    }

    @Override
    public int getNumberOfBooks() {
        return bookRepository.findAll().size();
    }
}
