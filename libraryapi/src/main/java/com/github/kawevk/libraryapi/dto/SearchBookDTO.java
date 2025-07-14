package com.github.kawevk.libraryapi.dto;

import com.github.kawevk.libraryapi.model.BookGender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SearchBookDTO(
        UUID id,
        String title,
        String isbn,
        LocalDate publicationDate,
        BookGender gender,
        BigDecimal price,
        AuthorDTO author
) {
}
