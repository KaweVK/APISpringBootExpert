package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void save() {
        Author author = new Author();
        author.setName("John");
        author.setNationality("Brasil");
        author.setBirthDate(LocalDate.of(2005, 4, 25));

        var savedAuthor = authorRepository.save(author);
    }

    @Test
    public void update() {
        var id = UUID.fromString("b9f06d16-23b3-4bc8-9850-f3a71d1df614");

        Optional<Author> authorFinded = authorRepository.findById(id);
        if (authorFinded.isPresent()) {
            Author author = authorFinded.get();
            author.setName("John Doe");
            author.setNationality("USA");
            authorRepository.save(author);
        }
    }

}
