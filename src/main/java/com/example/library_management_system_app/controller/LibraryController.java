package com.example.library_management_system_app.controller;

import ApiResponse.ApiResponse;
import ExistingUserException.ExistingUserException;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.exception.UserNotFoundException;
import com.example.library_management_system_app.services.LibraryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class LibraryController {
    @Autowired
    private LibraryServices libraryServices;
    private ApiResponse apiResponse = new ApiResponse();

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        try {
            apiResponse.setUsers(libraryServices.getUsers());
            libraryServices.registerUser(userRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true, apiResponse.getUsers(),"user registration successful"), HttpStatus.OK);
        } catch (ExistingUserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("getUsers")
    public ResponseEntity<?> getUsers(){
        try{
            apiResponse.setUsers(libraryServices.getUsers());
            return new ResponseEntity<>(new ApiResponse(true,apiResponse.getUsers(), "found users successfully"), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/removeUser")
    public ResponseEntity<?>removeUser(@RequestBody UserRegisterRequest userRegisterRequest){
        try{
            apiResponse.setUsers(libraryServices.getUsers());
            libraryServices.removeUserBy(userRegisterRequest.getUsername());
            return new ResponseEntity<>(new ApiResponse(true,apiResponse.getUsers(),"user successfully removed"),HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),  HttpStatus.BAD_REQUEST);
        }
    }


}


