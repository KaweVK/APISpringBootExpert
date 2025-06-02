package com.github.kawevk.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;
    @Column(name = "title", nullable = false, length = 150)
    private String title;
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 30)
    private BookGender gender;
    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;
}
