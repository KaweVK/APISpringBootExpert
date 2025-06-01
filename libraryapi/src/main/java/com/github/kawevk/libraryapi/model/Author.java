package com.github.kawevk.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "author", schema = "public")
@Getter //em tempo de compliação, gera os métodos getters
@Setter //em tempo de compliação, gera os métodos setters
public class Author {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality;

    @Deprecated
    public Author() {
        //paara o Hibernate
    }

}
