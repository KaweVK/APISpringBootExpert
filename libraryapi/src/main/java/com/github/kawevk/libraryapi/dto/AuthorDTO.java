package com.github.kawevk.libraryapi.dto;

import com.github.kawevk.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO
        (UUID id,
         @NotBlank(message = "Name cannot be blank")
         String name,
         @NotNull(message = "Birth date is required")
         LocalDate birthDate,
         @NotBlank(message = "Nationality cannot be blank")
         String nacionality) {

    public Author MapToAuthor() {
        Author author = new Author();
        author.setName(name);
        author.setBirthDate(birthDate);
        author.setNationality(nacionality);
        return author;
    }

}
