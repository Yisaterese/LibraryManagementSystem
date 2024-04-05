package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.UserRegisterRequest;

import java.util.List;


public interface LibraryServices {
    void registerUser(UserRegisterRequest registerRequest);

    Long getNumberOfUsers();

    void removeUserBy(String username);

    List<User> getUsers();
}
