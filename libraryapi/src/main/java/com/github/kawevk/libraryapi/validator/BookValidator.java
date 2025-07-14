package com.github.kawevk.libraryapi.validator;

import com.github.kawevk.libraryapi.exception.DuplicatedRegisterException;
import com.github.kawevk.libraryapi.exception.InvalidFieldException;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository bookRepository;
    private static final int year_price_required = 2020;

    public void validar(Book book) {
        if(hasBookWithIsbn(book)) {
            throw new DuplicatedRegisterException("Já existe um livro cadastrado com o ISBN: " + book.getIsbn());
        }

        if (isNeededPrice(book)) {
            throw new InvalidFieldException("price", "O preço é necessário se o livro foi publicado a partir de 2020");
        }
    }

    private boolean isNeededPrice(Book book) {
        return book.getPrice() == null && book.getPublicationDate() != null
                && book.getPublicationDate().getYear() >= year_price_required;
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
