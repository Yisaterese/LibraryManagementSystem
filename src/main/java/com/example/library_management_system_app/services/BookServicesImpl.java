package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.DeleteBookRequest;
import com.example.library_management_system_app.dto.utility.Response.DeleteAllBooksResponse;
import com.example.library_management_system_app.dto.utility.Response.DeleteBookResponse;
import com.example.library_management_system_app.dto.utility.Response.UpdateBookResponse;
import com.example.library_management_system_app.exception.BookIsNotBorrowedException;
import com.example.library_management_system_app.exception.BookNotFoundException;
import com.example.library_management_system_app.exception.ExistingBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.library_management_system_app.dto.utility.Mapper.mapBookUpdate;
import static com.example.library_management_system_app.dto.utility.Mapper.mapDeleteBook;

@Service
public class BookServicesImpl implements BookServices {
    @Autowired
    BookRepository bookRepository;
    @Override
    public Book createBook(BookRequest bookRequest) {
        bookRepository.findAll().forEach(book -> {if(book.getIsbn().equals(bookRequest.getIsbn()))throw new ExistingBookException("Book with this isbn already exist");
        });
        Book newBook = new Book();
        newBook.setTitle(bookRequest.getTitle());
        newBook.setIsbn(bookRequest.getIsbn());
        newBook.setAuthor(bookRequest.getAuthor());
        bookRepository.save(newBook);
        return newBook;
    }
    @Override
    public int getNumberOfBooks() {
        return bookRepository.findAll().size();
    }



    @Override
    public Book findBookByAuthorAndTitle(Author author, String title) {
        List<Book> foundBooks = bookRepository.findAll();
        for(Book book: foundBooks){
            if(book.getAuthor().equals(author) && book.getTitle().equals(title)){return book;}
        }
        throw new BookNotFoundException("book with author's name "+author+" and title "+title+" not found");
    }

    @Override
    public Book findBookByAuthorAndTitle(String  authorName, String title) {
        List<Book> foundBooks = bookRepository.findAll();
        for(Book book: foundBooks){
            if(book.equals(authorName)){
                return book;
            }
        }
        throw new BookNotFoundException("book with author's name "+authorName+"and title "+title+"not found");
    }
    @Override
    public DeleteBookResponse deleteBookByTitle(DeleteBookRequest deleteBookRequest) {
        Book foundBook = findBookByIsbn(deleteBookRequest.getIsbn());
        if(foundBook == null)throw new BookNotFoundException("book with title "+deleteBookRequest.getTitle()+" not found");
       if(!foundBook.getTitle().equals(deleteBookRequest.getTitle()))throw new BookNotFoundException("Book not found");
       bookRepository.delete(foundBook);
       return mapDeleteBook(foundBook);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {if (book.isBorrowed())borrowedBooks.add(book);});
        return borrowedBooks;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return getBookBy(isbn);
    }

    @Override
    public DeleteAllBooksResponse deleteAllBooks() {
        DeleteAllBooksResponse deleteAllBooksResponse = new DeleteAllBooksResponse();
        bookRepository.deleteAll();
        return deleteAllBooksResponse;
    }

    private Book getBookBy(String isbn) {
        List<Book> foundBooks = bookRepository.findAll();
        for(Book book: foundBooks){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        throw new BookNotFoundException("book with isbn is not found");
    }

    @Override
    public Book    returnBookBorrowed(Book bookTitle) {
        Book foundBook = findBookByTitle(bookTitle.getTitle());
        if (foundBook == null) throw new BookNotFoundException("The book with title " + bookTitle + " not found");
        if (!foundBook.isBorrowed()) throw new BookIsNotBorrowedException("The book with title " + bookTitle+ " has not been borrowed");
        foundBook.setBorrowed(false);
        foundBook.setReturnedDate(LocalDate.now());
        updateBookStatus(foundBook);
        return foundBook;
    }

    @Override
    public Author findAuthorBy(Book book) {

        return null;
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
        if (isExistingBook == null) throw new BookNotFoundException("book not found");

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