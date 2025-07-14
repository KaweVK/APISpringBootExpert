package com.github.kawevk.libraryapi.service;

import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(UUID uuid) {
        return bookRepository.findById(uuid);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
