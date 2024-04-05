package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.library_management_system_app.dto.utility.Mapper.mapRegisterUser;

@RequiredArgsConstructor
@Service
public class UserServicesImpl implements UserServices {
    private UserRepository userRepository;
    @Override
    public Long getNumberOfUsers() {
       return (long) userRepository.findAll().size();
    }
    @Override
    public void save(User newUser) {
        userRepository.save(newUser);
    }
    @Override
    public void removeByUsername(String username) {
        User foundUser = userRepository.findByUserName(username);
        if(foundUser == null) throw new UserNotFoundException("user with username "+username+" not found");
        userRepository.deleteByUserName(username);
    }
    @Override
    public List<User> getUser() {
        List<User> foundUsers =userRepository.findAll();
        if(foundUsers.isEmpty())throw new UserNotFoundException("no user found");
        return foundUsers;
    }
    @Override
    public RegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User newUser = new User();
        newUser.setPassword(userRegisterRequest.getPassword());
        newUser.setUserName(userRegisterRequest.getUsername());
        newUser.setEmail(userRegisterRequest.getEmail());
        userRepository.save(newUser);
        return mapRegisterUser(newUser);
    }
}
