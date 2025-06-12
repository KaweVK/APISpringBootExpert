package com.github.kawevk.libraryapi.dto;

import java.time.LocalDate;

public record AuthorDTO(String name, LocalDate birthDate, String nacionality) {
}
