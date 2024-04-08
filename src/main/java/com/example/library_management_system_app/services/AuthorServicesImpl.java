package com.example.library_management_system_app.services;

import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.repository.AuthorRepository;
import com.example.library_management_system_app.dto.AuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServicesImpl implements AuthorServices{
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public Author registerAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setFirstname(authorRequest.getFirstname());
        author.setLastname(authorRequest.getLastname());
        author.setEmail(authorRequest.getEmail());
        author.setGender(authorRequest.getGender());
        author.setNationality(authorRequest.getNationality());
        author.setDateOfBirth(authorRequest.getDateOfBirth());
        author.setAutobiography(authorRequest.getAutobiography());
        author.setContactInfo(authorRequest.getContactInfo());
        authorRepository.save(author);
        return author;
       // return mapAuthorResponse(author);
    }
}
