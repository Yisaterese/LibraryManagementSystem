package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.Librarian;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.data.repository.LibrarianRepository;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.exception.ExistingUserException;
import com.example.library_management_system_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.library_management_system_app.dto.utility.Mapper.mapRegister;
@Service
public class LibrarianServicesImpl implements LibrarianServices {
    @Autowired
    private  LibrarianRepository librarianRepository;
    @Autowired
    private UserServices userServicesImpl;
    @Autowired
    private BookServices bookServices;
    @Override
    public RegisterResponse registerLibrarian(RegisterRequest registerRequest) {
        Librarian isExistingLibrarian = librarianRepository.findByUsername(registerRequest.getUsername());
        if (isExistingLibrarian == null) {
            Librarian librarian = new Librarian();
            librarian.setUsername(registerRequest.getUsername());
            librarian.setPassword(librarian.getPassword());
            librarian.setEmail(registerRequest.getEmail());
            librarianRepository.save(librarian);
            return mapRegister(librarian);
        }
        throw new ExistingUserException("Username taken");
    }
    @Override
    public int getNumberOfLibrarians() {
        return librarianRepository.findAll().size();
    }
    @Override
    public void deleteByUsername(String username) {
        Librarian foundLibrarian = librarianRepository.findByUsername(username);
        if(foundLibrarian == null) throw new UserNotFoundException("user with username "+username+" not found");
        librarianRepository.deleteByUsername(username);
    }
    @Override
    public AddBookResponse addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest) {
       return bookServices.addBookToLibrary(bookRequest, authorRequest);
    }
    @Override
    public int getNumberOfBooks() {
        return bookServices.getNumberOfBooks();
    }

    @Override
    public void deleteBookByTitle(String title) {
        bookServices.deleteBookByTitle(title);
    }

    @Override
    public void recordBookBorrower(String userName) {
        User isExistingUser = userServicesImpl.findUserByUsername(userName.toLowerCase()    );
        if(isExistingUser == null)throw new UserNotFoundException("'user not found");
        if(isExistingUser.isBorrowBook())isExistingUser.setBorrowBook(true);
        userServicesImpl.save(isExistingUser);
    }

    @Override
    public Book findBookByAuthorAndTitle(Author author, String title) {
        return bookServices.findBookByAuthorAndTitle(author,title);
    }

//    @Override
//    public Book findBookByAuthorAndTitle(Author author, String title) {
//        return bookServices.findBookByAuthorAndTitle(author,title);
//    }

}
