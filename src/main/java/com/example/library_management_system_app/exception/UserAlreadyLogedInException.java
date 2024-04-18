package com.example.library_management_system_app.exception;

public class UserAlreadyLogedInException extends LibraryRuntimeException {
    public UserAlreadyLogedInException(String string) {
        super(string);
    }
}
