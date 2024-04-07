package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.repository.AuthorRepository;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.library_management_system_app.dto.utility.Mapper.mapBook;
@Service
public class BookServicesImpl implements BookServices {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorServices authorServices;
    @Override
    public AddBookResponse addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest) {
        Author foundAuthor = authorServices.registerAuthor(authorRequest);
        Book book = new Book();
        book .setAuthor(foundAuthor);
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
