package com.example.library_management_system_app.exception;

public class ExistingBookException extends RuntimeException{
    public ExistingBookException(String message){
        super(message);
    }
}
