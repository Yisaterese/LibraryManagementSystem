package com.example.library_management_system_app.exception;

public class ExistingUserException extends  LibraryRuntimeException{
    public ExistingUserException(String message){
        super(message);
    }
}
