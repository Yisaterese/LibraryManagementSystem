package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.Librarian;
import com.example.library_management_system_app.data.repository.LibrarianRepository;
import com.example.library_management_system_app.dto.BookRequest;
import com.example.library_management_system_app.dto.UserRegisterRequest;
import com.example.library_management_system_app.dto.utility.Response.RegisterResponse;
import com.example.library_management_system_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.library_management_system_app.dto.utility.Mapper.mapRegister;

@Service
public class LibrarianServicesImpl implements LibrarianServices {
    @Autowired
    private BookServices bookServices;
    @Autowired
    private  LibrarianRepository librarianRepository;
    @Autowired
    private UserServices userServices;

    @Override
    public RegisterResponse registerLibrarian(UserRegisterRequest registerRequest) {
        Librarian librarian = new Librarian();
        librarian.setUsername(registerRequest.getUsername());
        librarian.setPassword(librarian.getPassword());
        librarian.setEmail(registerRequest.getEmail());
        librarianRepository.save(librarian);
        return mapRegister(librarian);
    }

    @Override
    public int getNumberOfUsers() {
        return librarianRepository.findAll().size();
    }

    @Override
    public void deleteByUsername(String username) {
        Librarian foundLibrarian = librarianRepository.findByUsername(username);
        System.out.println(foundLibrarian);
        if(foundLibrarian == null) throw new UserNotFoundException("user with username "+username+" not found");
        librarianRepository.deleteByUsername(username);
    }

    @Override
    public void addBookToLibrary(BookRequest bookRequest) {
        bookServices.addBook(bookRequest);
    }

    @Override
    public int getNumberOfBooks() {
        return bookServices.getNumberOfBooks();
    }
}
