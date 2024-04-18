package com.example.library_management_system_app.exception;

public class BookIsNotBorrowedException extends LibraryRuntimeException {
    public BookIsNotBorrowedException(String message) {
        super(message);
    }
}
