package com.github.kawevk.libraryapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO
        (UUID id,
         @NotBlank(message = "Name cannot be blank")
         @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
         String name,
         @NotNull(message = "Birth date is required")
         @Past(message = "Birth date must be in the past")
         LocalDate birthDate,
         @NotBlank(message = "Nationality cannot be blank")
         @Size(min = 2, max = 50, message= "Nationality must be between 2 and 50 characters")
         String nationality) {
}
