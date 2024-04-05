package com.example.library_management_system_app.data.repository;

import com.example.library_management_system_app.data.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
