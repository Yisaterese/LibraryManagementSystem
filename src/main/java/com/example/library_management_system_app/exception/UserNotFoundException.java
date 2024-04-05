package com.example.library_management_system_app.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
    super(message);
    }
}
