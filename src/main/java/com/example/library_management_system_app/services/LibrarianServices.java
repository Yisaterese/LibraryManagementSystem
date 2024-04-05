package com.example.library_management_system_app.services;

import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface LibrarianServices {
    RegisterResponse registerLibrarian(UserRegisterRequest registerRequest);

    int getNumberOfUsers();

    void deleteByUsername(String username);
}
