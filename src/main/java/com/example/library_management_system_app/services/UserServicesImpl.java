package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.dto.*;
import com.example.library_management_system_app.dto.utility.Response.*;
import com.example.library_management_system_app.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.library_management_system_app.dto.utility.Mapper.*;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BookServices bookServicesImpl;
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
        if (isExistingUser != null)  throw new ExistingUserException("username taken");
        User newUser = new User();
        newUser.setPassword(registerRequest.getPassword());
        newUser.setUserName(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        userRepository.save(newUser);
        return mapRegisterUser(newUser);
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
        User foundUser = userRepository.findByUserName(username);
        if (foundUser == null) throw new UserNotFoundException("user not found ");

        return foundUser;
    }

    @Override
    public Book returnBookBorrowed(Book bookTitle) {
       return bookServicesImpl.returnBookBorrowed(bookTitle);
    }

    @Override
    public User findByUserName(String lowerCase) {
        return null;
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        User isExistingUser = userRepository.findByUserName(logoutRequest.getUsername());
        validateUser(isExistingUser);
        validateIfUserIsLoggedOut(isExistingUser);
        isExistingUser.setLogin(false);
        userRepository.save(isExistingUser);
        return mapLoggOut(isExistingUser);
    }
    @Override
    public ReturnBorrowedBookResponse returnBorrowedBook(ReturnedBorrowedBookRequest request) {
        Book foundBook = bookServicesImpl.findBookByIsbn(request.getIsbn());
        User foundUser = userRepository.findByUserName(request.getBorrower());
        if (!foundBook.isBorrowed()) throw new BookNotFoundException("This book has not been borrowed ");
        if (!foundUser.isLogin()) throw new UserNotLoggedInException("user not logged in");
        List<Book> books = foundUser.getBookBorrowed();
        for(Book book:books){
            if(book.equals(foundBook)){
                books.remove(book);
            }
        }
        foundUser.setReturnedDate(LocalDate.now());
        foundBook.setReturnedDate(LocalDate.now());
        userRepository.save(foundUser);
        bookServicesImpl.save(foundBook);
        return mapReturnedBook(foundBook);
    }

    @Override
    public List<Book> returnedBorrowedBooks() {
        return null;
    }

    private static void validateIfUserIsLoggedOut(User isExistingUser) {
        if(isExistingUser.isLogin())throw new AlreadyLogOutException("user Already logged out.");
    }

    @Override
    public LoginResponse login(LoginRequest longinRequest){
        User isExistingUser = userRepository.findByUserName(longinRequest.getUsername());
        validateUser(isExistingUser);
        validateIfUserIsLoggedIn(isExistingUser);
        validateLoginRequest(longinRequest, isExistingUser);
        isExistingUser.setLogin(true);
        userRepository.save(isExistingUser);
        return mapLogin(isExistingUser);
    }


    private static void validateLoginRequest(LoginRequest longinRequest, User isExistingUser) {
        //if (!isExistingUser.getUserName().equals(longinRequest.getUsername()))throw new InvalidLoginRequest("Access denied");
        if(!isExistingUser.getPassword().equals(longinRequest.getPassword()))throw new InvalidLoginRequestException("Access denied");
    }

    private static void validateUser(User isExistingUser) {
        if(isExistingUser == null)throw new ExistingUserException("user does not exist");
    }

    private static void validateIfUserIsLoggedIn(User isExistingUser) {
        if(isExistingUser.isLogin())throw new UserAlreadyLogedInException("User already logged in");
    }

    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest bookRequest) {
        User foundUser = findUserByUsername(bookRequest.getUsernameOfBorrower());
        Book existingBook = bookServicesImpl.findBookByIsbn(bookRequest.getIsbn());
        if (existingBook == null) throw new BookNotFoundException("Book with title '" + bookRequest.getBookTitle() + "' not found");

        if (existingBook.isBorrowed()) throw new AlreadyBorrowedBookException("Book with title '" + bookRequest.getBookTitle() + "' has already been borrowed");
        existingBook.setBorrowed(true);

        existingBook.setBorrowedDate(LocalDate.now());
        bookServicesImpl.updateBookStatus(existingBook);

        if (foundUser == null) throw new UserNotFoundException("User not found");
        if (foundUser.isBorrowBook()) throw new UserAlreadyBorrowedException("User '" +bookRequest.getUsernameOfBorrower()+ "' has already borrowed a book");
        List<Book> userBorrowedBooks = foundUser.getBookBorrowed();
        userBorrowedBooks.add(existingBook);
        foundUser.setBookBorrowed(userBorrowedBooks);
        foundUser.setBorrowBook(true);
        foundUser.setDateBorrowed(LocalDate.now());
        save(foundUser);
        return mapBorrowBookResponse(existingBook);
    }
}

