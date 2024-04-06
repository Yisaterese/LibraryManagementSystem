package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.data.repository.LibrarianRepository;
import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class LibraryServiceTest {
    @Autowired
    private LibraryServices libraryServicesImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public BookServices bookServices;
    @Autowired
    private LibrarianServices librarianServices;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
        librarianRepository.deleteAll();
        bookRepository.deleteAll();
    }
    @Test
    public void registerUserTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("userame");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newUser@gmaol.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());
    }
    @Test
    public void registerTwoUsers_numberOfUsersIsTwoTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username1");
        registerRequest.setPassword("password");
        registerRequest.setEmail("username@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());

        registerRequest.setUsername("newUser");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newUser@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(2,libraryServicesImpl.getNumberOfUsers());
    }
    @Test
    public void registerTwoUsers_deleteOneTest() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        Assertions.assertEquals(0,libraryServicesImpl.getNumberOfUsers());
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        registerRequest.setEmail("username@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());

        registerRequest.setUsername("newUser");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newUser@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(2,libraryServicesImpl.getNumberOfUsers());

        libraryServicesImpl.removeUserBy(registerRequest.getUsername());
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());
    }
    @Test
    public void registerLibrarianTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        Assertions.assertEquals(0,libraryServicesImpl.getNumberOfUsers());
        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);
        Assertions.assertEquals(1,librarianServices.getNumberOfUsers());

    }
    @Test
    public void registerTwoLibrarian_removeOneTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        Assertions.assertEquals(0,libraryServicesImpl.getNumberOfUsers());
        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);
        Assertions.assertEquals(1,librarianServices.getNumberOfUsers());


        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);
        Assertions.assertEquals(2,librarianServices.getNumberOfUsers());
        libraryServicesImpl.deleteByUsername(registerRequest.getUsername());
        Assertions.assertEquals(1,librarianServices.getNumberOfUsers());

    }

    @Test
    public void addBookToLibraryTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        Author author = new Author();
        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setAuthor(author);
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(LocalDate.now());

        librarianServices.addBookToLibrary(bookRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfBooks());

    }

}