package com.github.kawevk.libraryapi.service;

import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthor(UUID id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(UUID idAuthor) {
        Optional<Author> authorOptional = authorRepository.findById(idAuthor);
        if (authorOptional.isPresent()) {
            authorRepository.delete(authorOptional.get());
        }
    }
}
