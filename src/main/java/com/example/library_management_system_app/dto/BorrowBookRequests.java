package com.example.library_management_system_app.dto;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.Data;

@Data
public class BorrowBookRequests {
    private RegisterRequest registerRequest;
    private BookRequest bookRequest;
}
