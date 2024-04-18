package com.example.library_management_system_app.exception;

public class UserNotFoundException extends LibraryRuntimeException{
    public UserNotFoundException(String message){
    super(message);
    }
}
