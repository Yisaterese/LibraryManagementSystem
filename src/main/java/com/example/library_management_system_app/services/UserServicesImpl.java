package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.example.library_management_system_app.dto.utility.Mapper.mapRegisterUser;
@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BookServices bookServicesImpl;
    @Autowired
    BookRepository bookRepository;

    @Override
    public int getNumberOfUsers() {
        return userRepository.findAll().size();
    }

    @Override
    public void save(User newUser) {
        userRepository.save(newUser);
    }

    @Override
    public void removeByUsername(String username) {
        User foundUser = userRepository.findByUserName(username);
        if (foundUser == null) throw new UserNotFoundException("user with username " + username + " not found");
        userRepository.deleteByUserName(username);
    }

    @Override
    public List<User> getUser() {
        List<User> foundUsers = userRepository.findAll();
        if (foundUsers.isEmpty()) throw new UserNotFoundException("no user found");
        return foundUsers;
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        User isExistingUser = userRepository.findByUserName(registerRequest.getUsername());
        if (isExistingUser == null) {
            User newUser = new User();
            newUser.setPassword(registerRequest.getPassword());
            newUser.setUserName(registerRequest.getUsername());
            newUser.setEmail(registerRequest.getEmail());
            userRepository.save(newUser);
            return mapRegisterUser(newUser);
        }
        throw new ExistingUserException("username taken");
    }

    @Override
    public Book userFindBookByAuthorAndTitle(Author author, String title) {
        return bookServicesImpl.findBookByAuthorAndTitle(author, title);
    }

    @Override
    public Book userFindBookByAuthorAndTitle(String author, String title) {
        return bookServicesImpl.findBookByAuthorAndTitle(author, title);
    }

    @Override
    public User findUserByUsername(String username) {
        User foundUser = userRepository.findByUserName(username.toLowerCase());
        if (foundUser == null) {
            throw new UserNotFoundException("user not found ");
        }
        return foundUser;
    }

    @Override
    public void returnBookBorrowed(String bookTitle) {
        bookServicesImpl.returnBookBorrowed(bookTitle);
    }
    @Override
    public void recordBookBorrower(String username) {
        User foundUser = userRepository.findByUserName(username.toLowerCase());
        if (foundUser == null)throw new UserNotFoundException("user not found");
        if(foundUser.isBorrowBook())foundUser.setBorrowBook(true);
        userRepository.save(foundUser);
    }
    @Override
    public Book borrowBook(RegisterRequest registerRequest, BookRequest bookRequest) {
        Book existingBook = bookServicesImpl.findBookByTitle(bookRequest.getTitle());
        try {if (existingBook == null) throw new BookNotFoundException("Book with title '" + bookRequest.getTitle() + "' not found");
        }catch (BookNotFoundException e){System.out.println(e.getMessage());}if (existingBook.isBorrowed()) throw new AlreadyBorrowedBookException("Book with title '" + bookRequest.getTitle() + "' has already been borrowed");
        existingBook.setBorrowed(true);
        existingBook.setBorrowedDate(bookRequest.getBorrowedDate());
        bookServicesImpl.updateBookStatus(existingBook);
        
        User foundUser = findUserByUsername(registerRequest.getUsername());
        try{if (foundUser == null) throw new UserNotFoundException("User not found");
        }catch (UserNotFoundException e){System.out.println(e.getMessage());}
        try {if (foundUser.isBorrowBook()) throw new UserAlreadyBorrowedException("User '" + registerRequest.getUsername() + "' has already borrowed a book");
        }catch (UserAlreadyBorrowedException e){System.out.println(e.getMessage());}
        foundUser.setBorrowBook(true);
        foundUser.setDateBorrowed(registerRequest.getDateBorrowed());
        save(foundUser);
        recordBookBorrower(registerRequest.getUsername());
        
        return existingBook;
    }
}

