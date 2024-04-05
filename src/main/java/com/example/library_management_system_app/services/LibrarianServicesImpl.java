package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Librarian;
import com.example.library_management_system_app.data.repository.LibrarianRepository;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.library_management_system_app.dto.utility.Mapper.mapRegister;

@RequiredArgsConstructor
@Service
public class LibrarianServicesImpl implements LibrarianServices {
    private final LibrarianRepository librarianRepository;
    @Override
    public RegisterResponse registerLibrarian(UserRegisterRequest registerRequest) {
        Librarian librarian = new Librarian();
        librarian.setUsername(registerRequest.getUsername());
        librarian.setPassword(librarian.getPassword());
        librarian.setEmail(registerRequest.getEmail());
        librarian.setDateOfBirth(registerRequest.getDateOfBirth());
        librarianRepository.save(librarian);
        return mapRegister(librarian);
    }
}
