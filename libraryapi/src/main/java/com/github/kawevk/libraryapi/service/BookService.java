package com.github.kawevk.libraryapi.service;

import com.github.kawevk.libraryapi.dto.SearchBookDTO;
import com.github.kawevk.libraryapi.mappers.BookMapper;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.model.BookGender;
import com.github.kawevk.libraryapi.repository.BookRepository;
import com.github.kawevk.libraryapi.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.github.kawevk.libraryapi.repository.specs.BookSpecs.*;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;
    private final BookMapper bookMapper;

    public Book createBook(Book book) {
        bookValidator.validar(book);
        return bookRepository.save(book);
    }

    public Optional<Book> findById(UUID uuid) {
        return bookRepository.findById(uuid);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public Page<SearchBookDTO> search(String isbn, String title, String authorName, BookGender bookGender, Integer publicationYear, Integer page, Integer size) {

        Specification<Book> specs = Specification
                .where( ((root, query, cb) -> cb.conjunction()) );

        if (isbn != null && !isbn.isEmpty()) {
            specs = specs.and(isbnEqual(isbn));
        }
        if (title != null && !title.isEmpty()) {
            specs = specs.and(titleLike(title));
        }
        if (bookGender != null) {
            specs = specs.and(bookGenderEqual(bookGender));
        }
        if (authorName != null && !authorName.isEmpty()) {
            specs = specs.and(authorNameLike(authorName));
        }
        if (publicationYear != null) {
            specs = specs.and(publicationYearEqual(publicationYear));
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<Book> pageResult = bookRepository.findAll(specs, pageable);

        Page<SearchBookDTO> result = pageResult.map(bookMapper::toDTO);

        return result;
    }

    public void updateBook(Book book) {
        if (book.getId() == null) {
            throw new IllegalArgumentException("Book ID must not be null for update");
        }

        bookValidator.validar(book);
        bookRepository.save(book);
    }
}
