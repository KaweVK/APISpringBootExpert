package com.github.kawevk.libraryapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO
        (UUID id,
         @NotBlank(message = "Nome não pode ser vazio")
         @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
         String name,
         @NotNull(message = "Data de nascimento é necessária")
         @Past(message = "Data de nascimento deve ser no passado")
         LocalDate birthDate,
         @NotBlank(message = "Nacionalidade não pode ser vazia")
         @Size(min = 2, max = 50, message= "Nacionalidade deve ter entre 2 e 50 caracteres")
         String nationality) {
}
