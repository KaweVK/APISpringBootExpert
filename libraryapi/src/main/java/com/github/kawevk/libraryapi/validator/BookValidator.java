package com.github.kawevk.libraryapi.validator;

import com.github.kawevk.libraryapi.exception.DuplicatedRegisterException;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository bookRepository;

    public void validar(Book book) {
        if(hasBookWithIsbn(book)) {
            throw new DuplicatedRegisterException("Já existe um livro cadastrado com o ISBN: " + book.getIsbn());
        }
    }

    private boolean hasBookWithIsbn(Book book) {
        Optional<Book> bookFinded = bookRepository.findByIsbn(book.getIsbn());

        if (book.getId() == null) {
            return bookFinded.isPresent();
        }

        return bookFinded
                .map(Book::getId)
                .stream()
                .anyMatch(id -> !id.equals(book.getId()));
    }
}
