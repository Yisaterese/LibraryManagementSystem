package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;

import java.util.List;

public interface UserServices {
    Long getNumberOfUsers();

    void save(User newUser);

    void removeByUsername(String username);

    List<User> getUser();
}
