package com.example.library_management_system_app.dto.utility.Response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnBorrowedBookResponse {
    private boolean message;
    private LocalDate returnedDate;
}
