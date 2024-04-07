package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.dto.RegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.exception.ExistingUserException.ExistingUserException;
import com.example.library_management_system_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.library_management_system_app.dto.utility.Mapper.mapRegisterUser;
@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Override
    public int getNumberOfUsers() {
       return  userRepository.findAll().size();
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
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        User isExistingUser = userRepository.findByUserName(registerRequest.getUsername());
        if (isExistingUser == null) {
            User newUser = new User();
            newUser.setPassword(registerRequest.getPassword());
            newUser.setUserName(registerRequest.getUsername());
            newUser.setEmail(registerRequest.getEmail());
            userRepository.save(newUser);
            return mapRegisterUser(newUser);
        }
        throw new ExistingUserException("username taken");
    }
}
