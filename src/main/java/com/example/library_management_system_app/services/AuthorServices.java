package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.dto.AuthorRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthorServices {
    Author registerAuthor(AuthorRequest authorRequest);
}
