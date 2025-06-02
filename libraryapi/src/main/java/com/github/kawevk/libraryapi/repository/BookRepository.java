package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
