package com.example.library_management_system_app.dto.utility;

import com.example.library_management_system_app.data.model.Librarian;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;

public class Mapper {
    public static RegisterResponse mapRegister(Librarian librarian){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(librarian.getId());
        registerResponse.setUsername(librarian.getUsername());
        registerResponse.setEmail(librarian.getEmail());
        return registerResponse;
    }
    public static RegisterResponse mapRegisterUser(User user){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUsername(user.getUserName());
        registerResponse.setEmail(registerResponse.getEmail());
        registerResponse.setId(user.getId());
        return registerResponse;
    }
}
