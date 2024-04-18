package com.example.library_management_system_app.exception;

public class AlreadyReturnedBookException extends LibraryRuntimeException {
    public AlreadyReturnedBookException(String string) {
        super(string);
    }
}
