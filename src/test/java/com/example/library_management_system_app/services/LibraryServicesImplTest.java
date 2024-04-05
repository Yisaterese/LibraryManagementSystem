package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.repository.UserRepository;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryServiceTest {
    @Autowired
    private LibraryServices libraryServicesImpl;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
    }
    @Test
    public void registerUserTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("userame");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newUser@gmaol.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());
    }

    @Test
    public void registerTwoUsers_numberOfUsersIsTwoTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username1");
        registerRequest.setPassword("password");
        registerRequest.setEmail("username@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());

        registerRequest.setUsername("newUser");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newUser@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(2,libraryServicesImpl.getNumberOfUsers());
    }

    @Test
    public void registerTwoUsers_deleteOneTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        Assertions.assertEquals(0,libraryServicesImpl.getNumberOfUsers());
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        registerRequest.setEmail("username@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());

        registerRequest.setUsername("newUser");
        registerRequest.setPassword("password");
        registerRequest.setEmail("newUser@gmail.com");
        libraryServicesImpl.registerUser(registerRequest);
        Assertions.assertEquals(2,libraryServicesImpl.getNumberOfUsers());

        libraryServicesImpl.removeUserBy(registerRequest.getUsername());
        Assertions.assertEquals(1,libraryServicesImpl.getNumberOfUsers());
    }

}