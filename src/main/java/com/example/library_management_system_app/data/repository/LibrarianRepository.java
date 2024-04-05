package com.example.library_management_system_app.data.repository;

import com.example.library_management_system_app.data.model.Librarian;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends MongoRepository<Librarian, String> {
    Librarian findByUsername(String username);
    void deleteByUsername(String username);
}
