package com.github.kawevk.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author", schema = "public")
@Data
@AllArgsConstructor
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

    //@OneToMany(mappedBy = "author")
    @Transient
    private List<Book> books;

    @Deprecated
    public Author() {
        //paara o Hibernate
    }

}
