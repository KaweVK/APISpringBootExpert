package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findByName(String name);

    List<Author> findByNationality(String nacionality);

    List<Author> findByNameAndNationality(String name, String nacionality);

    Optional<Author> findByNationalityAndNameAndBirthDate(String nationality, String name, LocalDate birthDate);
}

