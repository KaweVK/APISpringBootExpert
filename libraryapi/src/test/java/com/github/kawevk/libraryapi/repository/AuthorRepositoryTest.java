package com.github.kawevk.libraryapi.repository;

import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void findAll() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    @Test
    public void save() {
        Author author = new Author();
        author.setName("John");
        author.setNationality("Brasil");
        author.setBirthDate(LocalDate.of(2005, 4, 25));

        var savedAuthor = authorRepository.save(author);
    }

    @Test
    public void update() {
        var id = UUID.fromString("b9f06d16-23b3-4bc8-9850-f3a71d1df614");

        Optional<Author> authorFinded = authorRepository.findById(id);
        if (authorFinded.isPresent()) {
            Author author = authorFinded.get();
            author.setName("John Doe");
            author.setNationality("USA");
            authorRepository.save(author);
        }
    }

    @Test
    public void count() {
        long count = authorRepository.count();
        System.out.println("Total authors: " + count);
    }

    @Test
    void saveWithBooks() {
        Author author = new Author();
        author.setName("Coollen Hover");
        author.setNationality("USA");
        author.setBirthDate(LocalDate.of(1997, 1, 1));

        Book book = new Book();
        book.setIsbn("1234567890123");
        book.setTitle("The Great Adventure");
        book.setPublicationDate(LocalDate.of(2020, 5, 20));
        book.setGender(BookGender.FICCAO);
        book.setPrice(BigDecimal.valueOf(19.99));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setIsbn("1234567890124");
        book2.setTitle("The Bad Adventure");
        book2.setPublicationDate(LocalDate.of(2020, 5, 22));
        book2.setGender(BookGender.FICCAO);
        book2.setPrice(BigDecimal.valueOf(19.99));
        book2.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);
        author.getBooks().add(book2);

        authorRepository.save(author);
        bookRepository.saveAll(author.getBooks());
    }

    @Test
    void listBooksByAuthor() {
        var id = UUID.fromString("627bb44b-f220-444a-879f-dfe4c11ef57f");
        var author = authorRepository.findById(id).get();

        List<Book> books = bookRepository.findByAuthor(author);
        author.setBooks(books);

        author.getBooks().forEach(System.out::println);
    }

}
