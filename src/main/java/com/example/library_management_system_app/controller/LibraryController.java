package com.example.library_management_system_app.controller;

import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.AddBookApiResponse;
import com.example.library_management_system_app.dto.utility.Response.AddBookResponse;
import com.example.library_management_system_app.dto.utility.Response.ApiResponse;
import com.example.library_management_system_app.exception.BookNotFoundException;
import com.example.library_management_system_app.exception.ExistingBookException;
import com.example.library_management_system_app.exception.ExistingUserException;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.exception.UserNotFoundException;
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
    private LibraryServices libraryServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest userRegisterRequest) {
        try {
            RegisterResponse response = libraryServices.registerUser(userRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (ExistingUserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("getUsers")
    public ResponseEntity<?> getUsers(){
        try{
            List<User> users =  libraryServices.getUsers();
            return new ResponseEntity<>(new ApiResponse(true, users), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<?>removeUser(@PathVariable String username){
        try{
            libraryServices.deleteUserBy(username);
             return new ResponseEntity<>(new ApiResponse(true, username+" deleted successfully"),HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),  HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/registerLibrarian")
    public ResponseEntity<?>registerLibrarian(@RequestBody RegisterRequest userRegisterRequest){
        try{
            RegisterResponse response = libraryServices.registerLibrarian(userRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.OK);
        }catch (ExistingUserException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addBook")
    public ResponseEntity<?>addBookToLibrary(@RequestBody AddBookApiResponse addBookApiResponse){
         try{
             AddBookResponse response = libraryServices.addBookToLibrary(addBookApiResponse.getBookRequest(),addBookApiResponse.getAuthorRequest());
             return new ResponseEntity<>(new ApiResponse(true, response),HttpStatus.OK);
         }catch (ExistingBookException e){
             return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
         }
    }
    @GetMapping("/findBookBy/{authorName}/{bookTitle}")
    public ResponseEntity<?>findBookByTitleAndAuthor(@PathVariable String authorName, @PathVariable String bookTitle){
        try{
           Book bookResponse = libraryServices.findBookByAuthorAndTitle(authorName,bookTitle);
            return new ResponseEntity<>(new ApiResponse(true,bookResponse),HttpStatus.OK);
        }catch(ExistingBookException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBookBy{title}")
    public ResponseEntity<?>deleteBookByTitle(@PathVariable String title) {
        try{
            libraryServices.deleteBookByTitle(title);
            return new ResponseEntity<>(new ApiResponse(true,title+ "deleted successfully"),HttpStatus.OK);
        }catch (BookNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}


