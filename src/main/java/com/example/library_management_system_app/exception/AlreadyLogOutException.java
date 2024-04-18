package com.example.library_management_system_app.exception;

public class AlreadyLogOutException extends LibraryRuntimeException{
    public AlreadyLogOutException(String string) {
        super(string);
    }
}
