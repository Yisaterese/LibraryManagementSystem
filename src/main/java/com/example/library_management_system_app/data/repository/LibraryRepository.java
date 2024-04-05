package com.example.library_management_system_app.data.repository;

import com.example.library_management_system_app.data.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends MongoRepository<Library, String> {
}
