package com.example.library_management_system_app.data.repository;

import com.example.library_management_system_app.data.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryRepository extends MongoRepository<Library, String> {
}
