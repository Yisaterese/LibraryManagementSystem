package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.repository.AuthorRepository;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.AuthorResponse;
import com.example.library_management_system_app.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        String dateAddedToLibrary = bookRequest.getDateAddedToLibrary();
        LocalDate convertedDate = LocalDate.parse(dateAddedToLibrary);
        book.setDateAddedToLibrary(convertedDate);
        bookRepository.save(book);
        return mapBook(book);
    }
    @Override
    public int getNumberOfBooks() {
        return bookRepository.findAll().size();
    }

    @Override
    public Book findBookByAuthorAndTitle(Author author, String title) {
        List<Book> foundBooks = bookRepository.findAll();
        System.out.println(foundBooks);
        for(Book book: foundBooks){
            if(book.getAuthor().equals(author) && book.getTitle().equalsIgnoreCase(title)){return book;}
        }
        throw new BookNotFoundException("book with author's name "+author+"and title "+title+"not found");
    }
    @Override
    public Book findBookByAuthorAndTitle(String  authorName, String title) {
        List<Book> foundBooks = bookRepository.findAll();
        for(Book book: foundBooks){
            if(book.getAuthor().getFirstname().equalsIgnoreCase(authorName)
                    && book.getAuthor().getLastname().equalsIgnoreCase(authorName)
                    && book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }
        throw new BookNotFoundException("book with author's name "+authorName+"and title "+title+"not found");
    }
    @Override
    public void deleteBookByTitle(String title) {
        Book foundBook = bookRepository.findBookByTitle(title);
        if(foundBook == null)throw new BookNotFoundException("book with title "+title+" not found");
        bookRepository.delete(foundBook);
    }

    @Override
    public Book borrowbook(String title) {
        return null;
    }
}