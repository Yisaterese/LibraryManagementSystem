package com.example.library_management_system_app.controller;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.utility.Response.ApiResponse;
import com.example.library_management_system_app.controller.ExistingUserException.ExistingUserException;
import com.example.library_management_system_app.dto.UserRegisterRequest;
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
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
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
    @DeleteMapping("/removeUser")
    public ResponseEntity<?>removeUser(@RequestBody UserRegisterRequest userRegisterRequest){
        try{
            libraryServices.removeUserBy(userRegisterRequest.getUsername());
            return new ResponseEntity<>(new ApiResponse(true, userRegisterRequest.getUsername()+" deleted successfully"),HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),  HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/registerLibrarian")
    public ResponseEntity<?>registerLibrarian(@RequestBody UserRegisterRequest userRegisterRequest){
        try{
            RegisterResponse response = libraryServices.registerLibrarian(userRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.OK);
        }catch (ExistingUserException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


}


