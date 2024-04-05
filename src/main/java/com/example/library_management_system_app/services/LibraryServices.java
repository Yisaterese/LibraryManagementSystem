package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;

import java.util.List;
public interface LibraryServices {
    RegisterResponse registerUser(UserRegisterRequest registerRequest);
    RegisterResponse registerLibrarian(UserRegisterRequest userRegisterRequest);

    Long getNumberOfUsers();

    void removeUserBy(String username);

    List<User> getUsers();
}
