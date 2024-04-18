package com.example.library_management_system_app.exception;

public class UserNotLoggedInException extends LibraryRuntimeException {
    public UserNotLoggedInException(String string) {
        super(string);
    }
}
