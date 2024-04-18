package com.example.library_management_system_app.exception;

public class BookNotFoundException extends LibraryRuntimeException{
    public BookNotFoundException(String message){
        super(message);
    }

}
