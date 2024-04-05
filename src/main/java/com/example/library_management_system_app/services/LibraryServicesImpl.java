package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServicesImpl implements LibraryServices{
    @Autowired
    private UserServices userServices;
    @Override
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        User newUser = new User();
        newUser.setPassword(userRegisterRequest.getPassword());
        newUser.setUserName(userRegisterRequest.getUsername());
        newUser.setEmail(userRegisterRequest.getEmail());
        userServices.save(newUser);
    }

    @Override
    public Long getNumberOfUsers() {
        return userServices.getNumberOfUsers();
    }

    @Override
    public void removeUserBy(String username) {
        userServices.removeByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userServices.getUser();
    }
}

