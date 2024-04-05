package com.example.library_management_system_app.services;

import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;

public interface LibrarianServices {
    RegisterResponse registerLibrarian(UserRegisterRequest registerRequest);
}
