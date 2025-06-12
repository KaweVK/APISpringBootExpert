package com.github.kawevk.libraryapi.service;

import com.github.kawevk.libraryapi.dto.AuthorDTO;
import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

}
