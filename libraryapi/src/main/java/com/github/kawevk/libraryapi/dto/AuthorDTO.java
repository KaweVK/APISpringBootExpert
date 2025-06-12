package com.github.kawevk.libraryapi.dto;

import com.github.kawevk.libraryapi.model.Author;

import java.time.LocalDate;

public record AuthorDTO(String name, LocalDate birthDate, String nacionality) {

    public Author MapToAuthor() {
        Author author = new Author();
        author.setName(name);
        author.setBirthDate(birthDate);
        author.setNationality(nacionality);
        return author;
    }

}
