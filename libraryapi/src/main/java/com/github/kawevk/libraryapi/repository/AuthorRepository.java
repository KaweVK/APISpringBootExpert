package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
