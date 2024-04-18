package com.example.library_management_system_app.controller;

import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.*;
import com.example.library_management_system_app.dto.utility.Response.*;
import com.example.library_management_system_app.exception.*;
import com.example.library_management_system_app.services.LibraryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/user")
public class LibraryController {
    @Autowired
    LibraryServices libraryServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest userRegisterRequest) {
        try {
            RegisterResponse response = libraryServices.registerUser(userRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (LibraryRuntimeException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("getUsers")
    public ResponseEntity<?> getUsers(){
        try{
            List<User> users =  libraryServices.getUsers();
            return new ResponseEntity<>(new ApiResponse(true, users), HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        try{
            libraryServices.deleteUserBy(username);
             return new ResponseEntity<>(new ApiResponse(true, username+" deleted successfully"),HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),  HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/registerLibrarian")
    public ResponseEntity<?>registerLibrarian(@RequestBody RegisterRequest userRegisterRequest){
        try{
            RegisterResponse response = libraryServices.registerLibrarian(userRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addBook")
    public ResponseEntity<?>addBookToLibrary(@RequestBody BookRequest bookRequest){
         try{
             AddBookResponse response = libraryServices.addBookToLibrary(bookRequest);
             return new ResponseEntity<>(new ApiResponse(true, response),HttpStatus.OK);
         }catch (LibraryRuntimeException e){
             return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
         }
    }
    @GetMapping("/findBook")
    public ResponseEntity<?>findBookByTitleAndAuthor(@RequestBody FindBookRequest findBookRequest){
        try{
            Book bookResponse = libraryServices.findBookByAuthorAndTitle(findBookRequest.getIsbn(), findBookRequest.getBookTitle());
            return new ResponseEntity<>(new ApiResponse(true,bookResponse),HttpStatus.OK);
        }catch(LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteBook")
    public ResponseEntity<?>deleteBookByTitle(@RequestBody DeleteBookRequest deleteBookRequest) {
        try{
           DeleteBookResponse response = libraryServices.deleteBookByTitle(deleteBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getBorrowedBooks")
    public ResponseEntity<?>getBorrowedBooks(){
        try{
           List<Book> borrowedBooks = libraryServices.getBorrowedBooks();
            return new ResponseEntity<>(new ApiResponse(true,borrowedBooks),HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBook")
    public ResponseEntity<?>getBookByIsbn(FindBookRequest findBookRequest){
        try{
            Book book = libraryServices.findBookByIsbn(findBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,book),HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/borrowBook")
    public ResponseEntity<?>borrowBook(@RequestBody BorrowBookRequest bookRequests){
       try{
        BorrowBookResponse response =   libraryServices.borrowBook(bookRequests);
        return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.OK);
    }catch (LibraryRuntimeException e){
       return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);}
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
        try{
        LoginResponse loginResponse = libraryServices.login(loginRequest);
        return new ResponseEntity<>(new ApiResponse(true,loginResponse),HttpStatus.OK);
    }catch(LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest){
        try{
            LogoutResponse logoutResponse = libraryServices.logout(logoutRequest);
            return new ResponseEntity<>(new ApiResponse(true,logoutResponse),HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getReturnedBorrowedBook")
    public ResponseEntity<?>getReturnedBorrowedBooks(){
        try{
            List<Book> returnedBorrowedBooks = libraryServices.returnedBorrowedBooks();
            return new ResponseEntity<>(new ApiResponse(true,returnedBorrowedBooks),HttpStatus.OK);
        }catch (LibraryRuntimeException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBookBorrowed")
    public ResponseEntity<?>returnBookBorrowed(@RequestBody ReturnedBorrowedBookRequest request){
        try{
        ReturnBorrowedBookResponse response = libraryServices.returnBorrowedBookResponse(request);
        return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.OK);
    }catch (LibraryRuntimeException e){
        return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);}
    }
}


