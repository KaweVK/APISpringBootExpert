package com.github.kawevk.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author", schema = "public")
@Data
@AllArgsConstructor
@ToString(exclude = "books")
@EntityListeners(AuditingEntityListener.class) //usado para auditar as datas de criação e atualização
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
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;
    @CreatedDate
    @Column(name = "register_date")
    private LocalDateTime registerDate;
    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "user_id")
    private UUID userId;

    @Deprecated
    public Author() {
        //para o Hibernate
    }

}
