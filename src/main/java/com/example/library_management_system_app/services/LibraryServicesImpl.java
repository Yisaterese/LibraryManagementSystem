package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LibraryServicesImpl implements LibraryServices{
    @Autowired
    private UserServices userServices;
    @Autowired

    private LibrarianServices librarianServices;
    @Override
    public RegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        return userServices.registerUser(userRegisterRequest);
    }
    @Override
    public  RegisterResponse registerLibrarian(UserRegisterRequest userRegisterRequest){
       return librarianServices.registerLibrarian(userRegisterRequest);
    }
    @Override
    public int getNumberOfUsers() {return userServices.getNumberOfUsers();}
    @Override
    public void removeUserBy(String username) {userServices.removeByUsername(username);}
    @Override
    public List<User> getUsers() {return userServices.getUser();}

    @Override
    public void deleteByUsername(String username) {
        librarianServices.deleteByUsername(username);
    }


}

