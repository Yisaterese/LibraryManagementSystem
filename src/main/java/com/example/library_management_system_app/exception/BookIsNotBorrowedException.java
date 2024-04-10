package com.example.library_management_system_app.exception;

public class BookIsNotBorrowedException extends RuntimeException {
    public BookIsNotBorrowedException(String message) {
        super(message);
    }
}
