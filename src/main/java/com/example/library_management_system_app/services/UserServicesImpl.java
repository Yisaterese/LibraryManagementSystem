package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Long getNumberOfUsers() {
       return (long) userRepository.findAll().size();
//        return userRepository.count();
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
}
