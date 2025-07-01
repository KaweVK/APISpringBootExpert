package com.github.kawevk.libraryapi.service;

import com.github.kawevk.libraryapi.exception.OperationNotAllowedException;
import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.repository.AuthorRepository;
import com.github.kawevk.libraryapi.repository.BookRepository;
import com.github.kawevk.libraryapi.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorValidator authorValidator;
    @Autowired
    private BookRepository bookRepository;

    public Author createAuthor(Author author) {
        authorValidator.validate(author);
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthor(UUID id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(UUID idAuthor) {
        if (hasBooks(authorRepository.findById(idAuthor).get())) {
            throw new OperationNotAllowedException("Author with id " + idAuthor + " has books associated and cannot be deleted.");
        }

        Optional<Author> authorOptional = authorRepository.findById(idAuthor);
        if (authorOptional.isPresent()) {
            authorRepository.delete(authorOptional.get());
        }
    }

    public List<Author> searchAuthors(String name, String nacionality) {
        if (name != null && nacionality != null) {
            return authorRepository.findByNameAndNationality(name, nacionality);
        } else if (name == null && nacionality != null) {
            return authorRepository.findByNationality(nacionality);
        } else if (name != null && nacionality == null) {
            return authorRepository.findByName(name);
        }

        return authorRepository.findAll();

    }

    public void updateAuthor(Author author) {
        if (author.getId() == null) {
            throw new IllegalArgumentException("Author id must not be null");
        }
        authorRepository.save(author);
    }

    public boolean hasBooks(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
