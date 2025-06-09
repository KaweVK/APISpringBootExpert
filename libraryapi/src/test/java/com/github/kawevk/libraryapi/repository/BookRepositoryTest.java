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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void findAll() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    @Test
    void save() {
        Book book = new Book();
        book.setIsbn("99999-32999");
        book.setTitle("A biografia de Kawe");
        book.setPublicationDate(LocalDate.of(2005, 4, 25));
        book.setPrice(BigDecimal.valueOf(45.9));
        book.setGender(BookGender.BIOGRAFIA);

        Author author = authorRepository.findById(UUID.fromString("d92eb885-0784-4c35-a069-841a9b9c98e5")).orElse(null);
        book.setAuthor(author);

        var savedBook = bookRepository.save(book);
    }

    @Test
    void saveWithCascade() {
        Book book = new Book();
        book.setIsbn("99999-99929");
        book.setTitle("Verity");
        book.setPublicationDate(LocalDate.of(2005, 4, 25));
        book.setPrice(BigDecimal.valueOf(45.9));
        book.setGender(BookGender.ROMANCE);

        Author author = new Author();
        author.setName("Coollen Hoover");
        author.setBirthDate(LocalDate.of(1979, 12, 11));
        author.setNationality("American");

        book.setAuthor(author);

        var savedBook = bookRepository.save(book);
    }

    @Test
    void update() {
        var id = UUID.fromString("aaa769cd-8a1b-491b-ab66-e1cde57454cc");

        Optional<Book> bookFinded = bookRepository.findById(id);
        if (bookFinded.isPresent()) {
            Book book = bookFinded.get();
            book.setTitle("A Cinco Passos de Você - Edição Especial");
            bookRepository.save(book);
        }
    }

    @Test
    void count() {
        long count = bookRepository.count();
        System.out.println("Total authors: " + count);
    }

    @Test
    @Transactional
    void searchBookById() {
        UUID id = UUID.fromString("a9ad45e8-1800-440d-b208-79761f991cae");
        Book book = bookRepository.findById(id).orElse(null);
        System.out.println("Book: ");
        System.out.println(book.getTitle());
        System.out.println("Author: ");
        System.out.println(book.getAuthor().getName());
    }

    @Test
    void searchByTitle() {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("ve");
        books.forEach(System.out::println);
    }

    @Test
    void listAllWithQuery() {
        List<Book> books = bookRepository.listAll();
        books.forEach(System.out::println);
    }

    @Test
    void listAuthorWithBooks() {
        List<Author> authors = bookRepository.listAuthorWithBooks();
        authors.forEach(System.out::println);
    }

    @Test
    void listAllBookTitles() {
        List<String> titles = bookRepository.listAllBookTitles();
        titles.forEach(System.out::println);
    }

    @Test
    void findByGender() {
        List<Book> books = bookRepository.findByGender(BookGender.ROMANCE, "title");
        books.forEach(System.out::println);
    }

    @Test
    void deleteByGender() {
        bookRepository.deleteByGender(BookGender.BIOGRAFIA);
    }

}