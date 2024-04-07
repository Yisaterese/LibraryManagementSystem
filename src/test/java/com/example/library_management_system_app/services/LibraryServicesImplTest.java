package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.Librarian;
import com.example.library_management_system_app.data.repository.BookRepository;
import com.example.library_management_system_app.data.repository.LibrarianRepository;
import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.RegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

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
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("userame");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newUser@gmaol.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());
    }
    @Test
    public void registerTwoUsers_numberOfUsersIsTwoTest(){
        RegisterRequest registerRequest = new RegisterRequest();
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
        RegisterRequest registerRequest = new RegisterRequest();
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
        RegisterRequest registerRequest = new RegisterRequest();
        Librarian librarian = new Librarian();

        Assertions.assertEquals(0,libraryServicesImpl.getNumberOfUsers());
        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarian.setPassword(registerRequest.getPassword());
        librarian.setUsername(registerRequest.getUsername());
        librarian.setEmail(registerRequest.getEmail());

        librarianServices.registerLibrarian(registerRequest);
        Assertions.assertEquals(1,librarianServices.getNumberOfLibrarians());

    }
    @Test
    public void registerTwoLibrarian_removeOneTest() {

        Assertions.assertEquals(0,libraryServicesImpl.getNumberOfUsers());
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);
        Assertions.assertEquals(1,librarianServices.getNumberOfLibrarians());

        registerRequest.setUsername("secondLibrarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);
        Assertions.assertEquals(2,librarianServices.getNumberOfLibrarians());
        libraryServicesImpl.deleteByUsername(registerRequest.getUsername());
        Assertions.assertEquals(1,librarianServices.getNumberOfLibrarians());

    }
    @Test
    public void addBookToLibraryTest() {
        AuthorRequest authorRequest = new AuthorRequest();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);


        authorRequest.setFirstname("Chinue");
        authorRequest.setLastname("Achebe");
        authorRequest.setGender("Male");
        authorRequest.setNationality("Nigerian");
        authorRequest.setAutobiography("I published my first book at very young age ");
        authorRequest.setContactInfo("12345-2455");
        authorRequest.setDateOfBirth(LocalDate.of(1965, Month.JANUARY,5));
        authorRequest.setEmail("chinueachebe@gmail.com");

        BookRequest bookRequest = new BookRequest();
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(LocalDate.now());

        librarianServices.addBookToLibrary(bookRequest,authorRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfBooks());
    }
    @Test
    public void addBooksToLibrary_findItByAuthor(){
        AuthorRequest authorRequest1 = new AuthorRequest();
        AuthorRequest authorRequest2 = new AuthorRequest();
        BookRequest bookRequest = new BookRequest();
        BookRequest bookRequest1 = new BookRequest();
        //registering author
        authorRequest1.setFirstname("Wole");
        authorRequest1.setLastname("Soyinka");
        authorRequest1.setGender("Male");
        authorRequest1.setNationality("Nigerian");
        authorRequest1.setAutobiography("I published my first book at very young age ");
        authorRequest1.setContactInfo("12345-2455");
        authorRequest1.setDateOfBirth(LocalDate.of(1930, Month.NOVEMBER,16));
        authorRequest1.setEmail("wolesoyinka@gmail.com");
        //adding book and the author
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(LocalDate.now());
        librarianServices.addBookToLibrary(bookRequest,authorRequest1);
        //registering second author
        authorRequest2.setFirstname("Chinue");
        authorRequest2.setLastname("Achebe");
        authorRequest2.setGender("Male");
        authorRequest2.setNationality("Nigerian");
        authorRequest2.setAutobiography("I published my first book at very young age ");
        authorRequest2.setContactInfo("12345-2455");
        authorRequest2.setDateOfBirth(LocalDate.of(1934, Month.JULY,13));
        authorRequest2.setEmail("chinueachebe@gmail.com");
        //adding second book and author
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Death and kings horseman");
        bookRequest.setDateAddedToLibrary(LocalDate.now());
        librarianServices.addBookToLibrary(bookRequest1, authorRequest2);

        Assertions.assertEquals(2, libraryServicesImpl.getNumberOfBooks());
    }

    @Test
    public void userSearchForBookByAuthorAndTitleest(){
        AuthorRequest authorRequest1 = new AuthorRequest();
        AuthorRequest authorRequest2 = new AuthorRequest();
        BookRequest bookRequest = new BookRequest();
        BookRequest bookRequest1 = new BookRequest();
        Author author1 = new Author();
        Book newBook = new Book();
        //registering author
        authorRequest1.setFirstname("Wole");
        authorRequest1.setLastname("Soyinka");
        authorRequest1.setGender("Male");
        authorRequest1.setNationality("Nigerian");
        authorRequest1.setAutobiography("I published my first book at very young age ");
        authorRequest1.setContactInfo("12345-2455");
        authorRequest1.setDateOfBirth(LocalDate.of(1930, Month.NOVEMBER,16));
        authorRequest1.setEmail("wolesoyinka@gmail.com");

        author1.setLastname(authorRequest1.getLastname());
        author1.setFirstname(authorRequest1.getFirstname());
        author1.setAutobiography(authorRequest1.getAutobiography());
        author1.setDateOfBirth(authorRequest1.getDateOfBirth());
        author1.setGender(authorRequest1.getGender());
        author1.setNationality(authorRequest1.getNationality());
        author1.setEmail(authorRequest1.getEmail());
        //adding book and the author
        bookRequest.setAuthor(author1);
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(LocalDate.now());
        newBook.setAuthor(bookRequest.getAuthor());
        newBook.setTitle(bookRequest.getTitle());
        newBook.setIsbn(bookRequest.getIsbn());
        librarianServices.addBookToLibrary(bookRequest,authorRequest1);
        //registering second author
        authorRequest2.setFirstname("Chinue");
        authorRequest2.setLastname("Achebe");
        authorRequest2.setGender("Male");
        authorRequest2.setNationality("Nigerian");
        authorRequest2.setAutobiography("I published my first book at very young age ");
        authorRequest2.setContactInfo("12345-2455");
        authorRequest2.setDateOfBirth(LocalDate.of(1934, Month.JULY,13));
        authorRequest2.setEmail("chinueachebe@gmail.com");
        //adding second book and author
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Death and kings horseman");
        bookRequest.setDateAddedToLibrary(LocalDate.now());
        librarianServices.addBookToLibrary(bookRequest1, authorRequest2);

        Assertions.assertEquals(2, libraryServicesImpl.getNumberOfBooks());
        Book newBook = libraryServicesImpl.findBookByAuthorAndTitle();
        Assertions.assertEquals(newBook, libraryServicesImpl.getNumberOfBooks());
    }


}