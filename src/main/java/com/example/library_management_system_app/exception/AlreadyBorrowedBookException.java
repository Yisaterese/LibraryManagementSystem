package com.example.library_management_system_app.exception;

public class AlreadyBorrowedBookException extends RuntimeException {
    public AlreadyBorrowedBookException(String message) {
        super(message);
    }
}
