package com.github.kawevk.libraryapi.service;

import com.github.kawevk.libraryapi.dto.AuthorDTO;
import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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

    public Author updateAuthor(UUID id, Author author) {
        Optional<Author> existingAuthor = authorRepository.findById(author.getId());
        if (existingAuthor.isPresent()) {
            Author updatedAuthor = existingAuthor.get();
            updatedAuthor.setName(author.getName());
            updatedAuthor.setBirthDate(author.getBirthDate());
            updatedAuthor.setNationality(author.getNationality());
            return authorRepository.save(updatedAuthor);
        }
        return null; // or throw an exception if preferred
    }
}
