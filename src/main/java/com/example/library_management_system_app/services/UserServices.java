package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServices {
    int getNumberOfUsers();

    void save(User newUser);

    void removeByUsername(String username);

    List<User> getUser();

    RegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
}
