package com.github.kawevk.libraryapi.dto;

import com.github.kawevk.libraryapi.model.BookGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterBookDTO(

        @ISBN
        @NotBlank(message = "Campo obrigatório")
        String isbn,
        @NotBlank(message = "Campo obrigatório")
        String title,
        @NotNull(message = "Campo obrigatório")
        @Past(message = "Data de publicação deve ser no passado")
        LocalDate publishedDate,
        BookGender gender,
        BigDecimal price,
        @NotNull(message = "Campo obrigatório")
        UUID authorId
) {
}
