package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.UpdateBookResponse;
import com.example.library_management_system_app.exception.BookIsNotBorrowedException;
import com.example.library_management_system_app.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.library_management_system_app.dto.utility.Mapper.mapBook;
import static com.example.library_management_system_app.dto.utility.Mapper.mapBookUpdate;
@Service
public class BookServicesImpl implements BookServices {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorServices authorServices;
    @Override
    public AddBookResponse addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest) {
        Author foundAuthor = authorServices.registerAuthor(authorRequest);
        Book book = new Book();
        book .setAuthor(foundAuthor);
        book.setTitle(bookRequest.getTitle());
       // String dateAddedToLibrary = bookRequest.getDateAddedToLibrary();
        //LocalDate convertedDate = LocalDate.parse(dateAddedToLibrary);
        book.setDateAddedToLibrary(LocalDate.now());
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
            System.out.println(book);
            if(book.getAuthor().equals(author) && book.getTitle().equals(title)){return book;}
            System.out.println(book);
        }
        throw new BookNotFoundException("book with author's name "+author+" and title "+title+" not found");
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
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }
    @Override
    public Book returnBookBorrowed(String bookTitle) {
        Book  foundBook = findBookByTitle(bookTitle);
        //System.out.println(foundBook );
        try {if (foundBook == null) throw new BookNotFoundException("The book with title " + bookTitle + " not found");
        }catch (BookNotFoundException e){System.out.println(e.getMessage());}
        try {if (!foundBook.isBorrowed()) throw new BookIsNotBorrowedException("The book with title " + bookTitle+ " has not been borrowed");
        }catch (BookIsNotBorrowedException e){System.out.println(e.getMessage());}
        foundBook.setBorrowed(false);
        //System.out.println(foundBook);
        foundBook.setReturnedDate(LocalDate.now());
        updateBookStatus(foundBook);
        //System.out.println(foundBook);

        return foundBook;
    }
    @Override
    public void save(Book book) {
        Book foundBook = bookRepository.findBookByTitle(book.getTitle());
        if (foundBook == null)throw new BookNotFoundException("book not found ");
        bookRepository.save(foundBook);
    }
    @Override
    public UpdateBookResponse updateBookStatus(Book bookStatus) {
        Book isExistingBook = findBookByTitle(bookStatus.getTitle());
        try {if (isExistingBook == null) throw new BookNotFoundException("book not found");
        }catch (BookNotFoundException e){System.out.println(e.getMessage());}
        isExistingBook.setAuthor(bookStatus.getAuthor());
        isExistingBook.setTitle(bookStatus.getTitle());
        isExistingBook.setIsbn(bookStatus.getIsbn());
        isExistingBook.setId(isExistingBook.getId());
        isExistingBook.setDateAddedToLibrary(bookStatus.getDateAddedToLibrary());
        isExistingBook.setBorrowedDate(bookStatus.getBorrowedDate());
        isExistingBook.setReturnedDate(bookStatus.getReturnedDate());
        isExistingBook.setBorrowed(bookStatus.isBorrowed());
        bookRepository.save(isExistingBook);
        return mapBookUpdate(isExistingBook);
    }
}