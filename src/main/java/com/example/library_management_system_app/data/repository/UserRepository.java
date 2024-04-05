package com.example.library_management_system_app.data.repository;

import com.example.library_management_system_app.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String>{
    User findByUserName(String username);
    void deleteByUserName(String     username);

//    void deleteUserByUserName(String username);
}
