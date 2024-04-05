package com.example.library_management_system_app.data.repository;

import com.example.library_management_system_app.data.model.Librarian;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibrarianRepository extends MongoRepository<Librarian, String> {
}
