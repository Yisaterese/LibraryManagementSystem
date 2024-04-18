package com.example.library_management_system_app.dto;

import lombok.Data;

@Data
public class BorrowBookRequest {
    private String usernameOfBorrower;
    private String bookTitle;
    private String isbn;
}
