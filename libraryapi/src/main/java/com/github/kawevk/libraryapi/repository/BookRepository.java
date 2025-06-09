package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.model.BookGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByAuthor(Author author);

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query(" select b from Book as b order by b.title, b.price")
    List<Book> listAll();

    @Query(" select a from Book b join b.author a order by a.name")
    List<Author> listAuthorWithBooks();

    @Query("select distinct l.title from Book l order by l.title")
    List<String> listAllBookTitles();

    @Query("select l from Book l where l.gender = :gender order by :order")
    List<Book> findByGender(@Param("gender") BookGender bookGender, @Param("order") String order);
}
