package com.example.library_management_system_app.dto;

import lombok.Data;

@Data
public class ReturnedBorrowedBookRequest {
    private String isbn;
    private String bookTitle;
    private String borrowerName;
}
