package com.example.library_management_system_app.exception;

public class ExistingUserException extends  RuntimeException{
    public ExistingUserException(String message){
        super(message);
    }
}
