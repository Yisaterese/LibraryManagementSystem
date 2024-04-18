package com.example.library_management_system_app.exception;

public class InvalidLoginRequestException extends LibraryRuntimeException {
    public InvalidLoginRequestException(String string) {
        super(string);
    }
}
