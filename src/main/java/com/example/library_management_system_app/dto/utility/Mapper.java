package com.example.library_management_system_app.dto.utility;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.Librarian;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.AuthorResponse;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.dto.utility.Response.UpdateBookResponse;

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
        registerResponse.setEmail(registerResponse.getEmail());
        registerResponse.setId(user.getId());
        return registerResponse;
    }
    public static AddBookResponse mapBook(Book book){
        AddBookResponse addBookResponse = new AddBookResponse();
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
        updateBookResponse.setAuthor(bookStatus.getAuthor());
        updateBookResponse.setTitle(bookStatus.getTitle());
        updateBookResponse.setIsbn(bookStatus.getIsbn());
        updateBookResponse.setDateAddedToLibrary(bookStatus.getDateAddedToLibrary());
        updateBookResponse.setBorrowedDate(bookStatus.getBorrowedDate());
        updateBookResponse.setReturnedDate(bookStatus.getReturnedDate());
        updateBookResponse.setBorrowed(bookStatus.isBorrowed());
        updateBookResponse.setId(bookStatus.getId());
        return updateBookResponse;

    }
}

