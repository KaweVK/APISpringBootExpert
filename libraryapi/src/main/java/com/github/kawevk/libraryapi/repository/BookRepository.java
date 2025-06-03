package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByAuthor(Author author);
}
