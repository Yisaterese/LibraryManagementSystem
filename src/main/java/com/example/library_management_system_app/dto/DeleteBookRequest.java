package com.example.library_management_system_app.dto;

import lombok.Data;

@Data
public class DeleteBookRequest {
    private String title;
    private String isbn;
}
