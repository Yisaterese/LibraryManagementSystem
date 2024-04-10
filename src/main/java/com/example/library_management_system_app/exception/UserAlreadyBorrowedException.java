package com.example.library_management_system_app.exception;

public class UserAlreadyBorrowedException extends RuntimeException {
    public UserAlreadyBorrowedException(String string) {
        super(string);
    }
}
