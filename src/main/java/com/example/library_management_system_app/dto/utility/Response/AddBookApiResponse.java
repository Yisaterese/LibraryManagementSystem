package com.example.library_management_system_app.dto.utility.Response;

import com.example.library_management_system_app.dto.AuthorRequest;
import com.example.library_management_system_app.dto.BookRequest;
import lombok.Data;

@Data
public class AddBookApiResponse {
    private BookRequest bookRequest;
    private AuthorRequest authorRequest;
}
