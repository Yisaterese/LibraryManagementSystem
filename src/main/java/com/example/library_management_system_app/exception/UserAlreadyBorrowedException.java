package com.example.library_management_system_app.exception;

public class UserAlreadyBorrowedException extends LibraryRuntimeException {
    public UserAlreadyBorrowedException(String string) {
        super(string);
    }
}
