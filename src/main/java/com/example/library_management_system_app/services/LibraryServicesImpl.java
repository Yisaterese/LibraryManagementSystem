package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.exception.BookNotFoundException;
import com.example.library_management_system_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LibraryServicesImpl implements LibraryServices{
    @Autowired
    private UserServices userServices;
    @Autowired
    private LibrarianServices librarianServices;
    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        return userServices.registerUser(registerRequest);
    }
    @Override
    public  RegisterResponse registerLibrarian(RegisterRequest registerRequest){
       return librarianServices.registerLibrarian(registerRequest);
    }
    @Override
    public int getNumberOfUsers() {return userServices.getNumberOfUsers();}
    @Override
    public void deleteUserBy(String username) {userServices.removeByUsername(username);}
    @Override
    public List<User> getUsers() {return userServices.getUser();}

    @Override
    public void deleteByUsername(String username) {
        librarianServices.deleteByUsername(username);

    }
    @Override
    public int getNumberOfBooks() {
        return librarianServices.getNumberOfBooks();
    }
    @Override
    public int getNumberOfLibrarians() {
        return librarianServices.getNumberOfLibrarians();
    }

    @Override
    public Book findBookByAuthorAndTitle(Author author, String title) {
           return userServices.userFindBookByAuthorAndTitle(author, title);
    }

    @Override
    public Book findBookByAuthorAndTitle(String authorName, String bookTitle) {
            return userServices.userFindBookByAuthorAndTitle(authorName, bookTitle);


    }
    public AddBookResponse addBookToLibrary(BookRequest bookRequest, AuthorRequest authorRequest){
        return librarianServices.addBookToLibrary(bookRequest,authorRequest);
    }
    @Override
    public void deleteBookByTitle(String title) {
        try {librarianServices.deleteBookByTitle(title);
        }catch (BookNotFoundException e){System.out.println( e.getMessage());}
    }



}

