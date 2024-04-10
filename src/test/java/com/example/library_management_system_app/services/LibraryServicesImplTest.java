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
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
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
    @Autowired
    private UserServices userServices;
    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
        librarianRepository.deleteAll();
        bookRepository.deleteAll();
    }
    @Test
    public void registerUserTest() {
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

        libraryServicesImpl.deleteUserBy(registerRequest.getUsername());
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
        authorRequest.setDateOfBirth(String.valueOf(LocalDate.of(1965, Month.JANUARY,5)));
        authorRequest.setEmail("chinueachebe@gmail.com");

        BookRequest bookRequest = new BookRequest();
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(String.valueOf(LocalDate.now()));

        librarianServices.addBookToLibrary(bookRequest,authorRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfBooks());
    }
    @Test
    public void addBooksToLibrary_findItByAuthorTest(){
        AuthorRequest authorRequest1 = new AuthorRequest();
        BookRequest bookRequest = new BookRequest();
        BookRequest bookRequest1 = new BookRequest();
        //registering author
        authorRequest1.setFirstname("Wole");
        authorRequest1.setLastname("Soyinka");
        authorRequest1.setGender("Male");
        authorRequest1.setNationality("Nigerian");
        authorRequest1.setAutobiography("I published my first book at very young age ");
        authorRequest1.setContactInfo("12345-2455");
        authorRequest1.setDateOfBirth(String.valueOf(LocalDate.of(1930, Month.NOVEMBER,16)));

        authorRequest1.setEmail("wolesoyinka@gmail.com");
        //adding book and the author
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(String.valueOf(LocalDate.now()));
        librarianServices.addBookToLibrary(bookRequest,authorRequest1);
        //registering second author
        authorRequest1.setFirstname("Chinue");
        authorRequest1.setLastname("Achebe");
        authorRequest1.setGender("Male");
        authorRequest1.setNationality("Nigerian");
        authorRequest1.setAutobiography("I published my first book at very young age ");
        authorRequest1.setContactInfo("12345-2455");
        authorRequest1.setDateOfBirth(String.valueOf(LocalDate.of(1934, Month.JULY,13)));
        authorRequest1.setEmail("chinueachebe@gmail.com");
        //adding second book and author
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Death and kings horseman");
        bookRequest.setDateAddedToLibrary(String.valueOf(LocalDate.now()));
        librarianServices.addBookToLibrary(bookRequest1, authorRequest1);

        Assertions.assertEquals(2, libraryServicesImpl.getNumberOfBooks());
    }
    @Test
    public void userSearchForBookByAuthorAndTitleTest(){
        AuthorRequest authorRequest1 = new AuthorRequest();
        BookRequest bookRequest1 = new BookRequest();
        Author author1 = new Author();
        Book newBook1 = new Book();
        //registering author
        authorRequest1.setFirstname("Wole");
        authorRequest1.setLastname("Soyinka");
        authorRequest1.setGender("Male");
        authorRequest1.setNationality("Nigerian");
        authorRequest1.setAutobiography("I published my first book at very young age ");
        authorRequest1.setContactInfo("12345-2455");
        authorRequest1.setDateOfBirth(String.valueOf(LocalDate.of(1930, Month.NOVEMBER,16)));
        authorRequest1.setEmail("wolesoyinka@gmail.com");
        authorRequest1.setContactInfo("_wolesoyinka@Ig");

        author1.setLastname(authorRequest1.getLastname());
        author1.setFirstname(authorRequest1.getFirstname());
        author1.setAutobiography(authorRequest1.getAutobiography());
        author1.setDateOfBirth(LocalDate.parse(authorRequest1.getDateOfBirth()));
        author1.setGender(authorRequest1.getGender());
        author1.setNationality(authorRequest1.getNationality());
        author1.setEmail(authorRequest1.getEmail());
        author1.setContactInfo(authorRequest1.getContactInfo());
        //adding book and the author
        bookRequest1.setAuthor(author1);
        bookRequest1.setIsbn("1234-34-1299");
        bookRequest1.setTitle("Things fall apart");
        bookRequest1.setDateAddedToLibrary(String.valueOf(LocalDate.now()));
        newBook1.setAuthor(bookRequest1.getAuthor());
        newBook1.setTitle(bookRequest1.getTitle());
        newBook1.setIsbn(bookRequest1.getIsbn());
        newBook1.setDateAddedToLibrary(LocalDate.parse(bookRequest1.getDateAddedToLibrary()));
        AddBookResponse bookResponse = librarianServices.addBookToLibrary(bookRequest1,authorRequest1);
        Book foundBook1 = libraryServicesImpl.findBookByAuthorAndTitle(author1,bookRequest1.getTitle());
        Assertions.assertEquals(bookResponse,foundBook1);
    }
//    @Test
//    public void userBorrowBookTest(){
//        RegisterRequest registerRequest = new RegisterRequest();
//        AuthorRequest authorRequest = new AuthorRequest();
//        BookRequest bookRequest = new BookRequest();
//
//        registerRequest.setUsername("username1");
//        registerRequest.setPassword("password");
//        registerRequest.setEmail("username@gmail.com");
//        libraryServicesImpl.registerUser(registerRequest);
//        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());
//
//        authorRequest.setFirstname("Chinue");
//        authorRequest.setLastname("Achebe");
//        authorRequest.setGender("Male");
//        authorRequest.setNationality("Nigerian");
//        authorRequest.setAutobiography("I published my first book at very young age ");
//        authorRequest.setContactInfo("12345-2455");
//        authorRequest.setDateOfBirth(String.valueOf(LocalDate.of(1965, Month.JANUARY,5)));
//        authorRequest.setEmail("chinueachebe@gmail.com");
//
//        bookRequest.setIsbn("1234-34-1299");
//        bookRequest.setTitle("Things fall apart");
//        bookRequest.setDateAddedToLibrary(String.valueOf(LocalDate.now()));
//
//        librarianServices.addBookToLibrary(bookRequest,authorRequest);
//        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfBooks());
//        Book borrowedBook = userServices.borrowBook(registerRequest,bookRequest);
//        Assertions.assertTrue(borrowedBook.isBorrowed());
//    }
    @Test
    public void userReturnBooksBorrowedTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        AuthorRequest authorRequest = new AuthorRequest();
        BookRequest bookRequest = new BookRequest();

        registerRequest.setUsername("username1");
        registerRequest.setPassword("password");
        registerRequest.setEmail("username@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());

        authorRequest.setFirstname("Chinue");
        authorRequest.setLastname("Achebe");
        authorRequest.setGender("Male");
        authorRequest.setNationality("Nigerian");
        authorRequest.setAutobiography("I published my first book at very young age ");
        authorRequest.setContactInfo("12345-2455");
        authorRequest.setDateOfBirth(String.valueOf(LocalDate.of(1965, Month.JANUARY,5)));
        authorRequest.setEmail("chinueachebe@gmail.com");

        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(String.valueOf(LocalDate.now()));

        librarianServices.addBookToLibrary(bookRequest,authorRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfBooks());

        Book borrowedBook = userServices.borrowBook(registerRequest,bookRequest);
        Assertions.assertTrue(borrowedBook.isBorrowed());
        System.out.println(borrowedBook);
        userServices.returnBookBorrowed(bookRequest.getTitle());
        Assertions.assertFalse(!borrowedBook.isBorrowed());
    }
    @Test
    public void librarianDeleteBookTitleTest(){
        RegisterRequest registerRequest = new RegisterRequest();

        AuthorRequest authorRequest = new AuthorRequest();
        registerRequest.setUsername("librarian");
        registerRequest.setPassword("password");
        registerRequest.setEmail("librarian@gmail.com");
        librarianServices.registerLibrarian(registerRequest);

        authorRequest.setFirstname("Chinue");
        authorRequest.setLastname("Achebe");
        authorRequest.setGender("Male");
        authorRequest.setNationality("Nigerian");
        authorRequest.setAutobiography("I published my first book at a very young age ");
        authorRequest.setContactInfo("12345-2455");
        authorRequest.setDateOfBirth(String.valueOf(LocalDate.of(1965, Month.JANUARY,5)));
        authorRequest.setEmail("chinueachebe@gmail.com");

        BookRequest bookRequest = new BookRequest();
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(String.valueOf(LocalDate.now()));

        librarianServices.addBookToLibrary(bookRequest, authorRequest);
        Assertions.assertEquals(1, libraryServicesImpl.getNumberOfBooks());

        librarianServices.deleteBookByTitle(bookRequest.getTitle());
        Assertions.assertEquals(0, libraryServicesImpl.getNumberOfBooks());
    }

    @Test
    public void userBorrowBookTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        AuthorRequest authorRequest = new AuthorRequest();
        registerRequest.setUsername("username1");
        registerRequest.setPassword("password");
        registerRequest.setEmail("username@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1, libraryServicesImpl.getNumberOfUsers());

        authorRequest.setFirstname("Chinue");
        authorRequest.setLastname("Achebe");
        authorRequest.setGender("Male");
        authorRequest.setNationality("Nigerian");
        authorRequest.setAutobiography("I published my first book at a very young age ");
        authorRequest.setContactInfo("12345-2455");
        authorRequest.setDateOfBirth(String.valueOf(LocalDate.of(1965, Month.JANUARY,5)));
        authorRequest.setEmail("chinueachebe@gmail.com");

        BookRequest bookRequest = new BookRequest();
        bookRequest.setIsbn("1234-34-1299");
        bookRequest.setTitle("Things fall apart");
        bookRequest.setDateAddedToLibrary(String.valueOf(LocalDate.now()));

        librarianServices.addBookToLibrary(bookRequest, authorRequest);
        Assertions.assertEquals(1, libraryServicesImpl.getNumberOfBooks());
        Book borrowedBook = userServices.borrowBook(registerRequest, bookRequest);
        Assertions.assertTrue(borrowedBook.isBorrowed());
    }


}