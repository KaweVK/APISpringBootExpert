package com.github.kawevk.libraryapi.service;

import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
}
