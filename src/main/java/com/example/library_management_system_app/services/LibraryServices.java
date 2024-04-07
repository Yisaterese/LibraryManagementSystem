package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LibraryServices {
    RegisterResponse registerUser(RegisterRequest registerRequest);
    RegisterResponse registerLibrarian(RegisterRequest userRegisterRequest);

    int getNumberOfUsers();

    void removeUserBy(String username);

    List<User> getUsers();

    void deleteByUsername(String username);

    int getNumberOfBooks();

    int getNumberOfLibrarians();
}
