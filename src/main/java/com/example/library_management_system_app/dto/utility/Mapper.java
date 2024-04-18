package com.example.library_management_system_app.dto.utility;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.Librarian;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.utility.Response.*;

import java.time.LocalDate;
import java.util.List;

public class Mapper {
    public static RegisterResponse mapRegister(Librarian librarian){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(librarian.getId());
        registerResponse.setUsername(librarian.getUsername());
        registerResponse.setEmail(librarian.getEmail());
        return registerResponse;
    }
    public static RegisterResponse mapRegisterUser(User user){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUsername(user.getUserName());
        registerResponse.setEmail(user.getEmail());
        registerResponse.setId(user.getId());
        return registerResponse;
    }
    public static ReturnBorrowedBookResponse mapReturnedBook(Book book){
        ReturnBorrowedBookResponse returnBorrowedBookResponse = new ReturnBorrowedBookResponse();
        returnBorrowedBookResponse.setMessage(true);
        returnBorrowedBookResponse.setReturnedDate(book.getReturnedDate());
        return returnBorrowedBookResponse;
    }

    public static Book mapBook(Book book){
        Book addBookResponse = new Book();
        addBookResponse.setAuthor(book.getAuthor());
        addBookResponse.setTitle(book.getTitle());
        addBookResponse.setIsbn(book.getIsbn());
        addBookResponse.setDateAddedToLibrary(book.getDateAddedToLibrary());
        return addBookResponse;
    }

    public static AuthorResponse mapAuthorResponse(Author author){
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setAutobiography(author.getAutobiography());
        authorResponse.setGender(author.getGender());
        authorResponse.setContactInfo(author.getContactInfo());
        authorResponse.setFirstname(author.getFirstname());
        authorResponse.setNationality(author.getNationality());
        authorResponse.setDateOfBirth(author.getDateOfBirth());
        authorResponse.setLastname(author.getLastname());
        authorResponse.setEmail(author.getEmail());
        return authorResponse;
    }
    public static UpdateBookResponse mapBookUpdate(Book bookStatus){
        UpdateBookResponse updateBookResponse = new UpdateBookResponse();
        updateBookResponse.setTitle(bookStatus.getTitle());
        updateBookResponse.setIsbn(bookStatus.getIsbn());
        updateBookResponse.setDateAddedToLibrary(bookStatus.getDateAddedToLibrary());
        updateBookResponse.setBorrowedDate(bookStatus.getBorrowedDate());
        updateBookResponse.setReturnedDate(bookStatus.getReturnedDate());
        updateBookResponse.setBorrowed(bookStatus.isBorrowed());
        updateBookResponse.setId(bookStatus.getId());
        return updateBookResponse;
    }
    public static BorrowBookResponse mapBorrowBookResponse(Book book){
        BorrowBookResponse bookResponse = new BorrowBookResponse();
        bookResponse.setTitle(book.getTitle());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setId(book.getId());
        bookResponse.setBorrowedDate(LocalDate.now());
        bookResponse.setBorrowed(true);
        return bookResponse;
    }

    public static AddBookResponse mapAddBookToLibrary(Book book){
        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setIsbn(book.getIsbn());
        addBookResponse.setId(book.getId());
        addBookResponse.setTitle(book.getTitle());
        addBookResponse.setAuthor(book.getAuthor());
        addBookResponse.setDateAddedToLibrary(book.getDateAddedToLibrary());
        addBookResponse.setBorrowedDate(book.getBorrowedDate());
        addBookResponse.setReturnedDate(book.getReturnedDate());
        return addBookResponse;
    }
    public static LoginResponse  mapLogin(User userr){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccessful(true);
        loginResponse.setUsername(userr.getUserName());
        loginResponse.setId(userr.getId());
        return loginResponse;
    }
    public static LogoutResponse mapLoggOut(User user){
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setSuccessful(false);
        return logoutResponse;
    }

    public static DeleteBookResponse mapDeleteBook(Book book){
        DeleteBookResponse deleteBookResponse = new DeleteBookResponse();
        deleteBookResponse.setDateDeleted(LocalDate.now());
        deleteBookResponse.setMessage(true);
        return deleteBookResponse;
    }
    public static BorrowedBooksResponse mapBorrowedBooks(List<Book> book){
        BorrowedBooksResponse borrowedBooksRequest = new BorrowedBooksResponse();
    return null;
    }
}

