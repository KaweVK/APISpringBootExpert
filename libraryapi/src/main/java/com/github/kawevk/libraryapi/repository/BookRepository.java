package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    boolean existsByAuthor(Author author);

    boolean existsByIsbn(String isbn);

    Optional<Book> findByIsbn(String isbn);
}
