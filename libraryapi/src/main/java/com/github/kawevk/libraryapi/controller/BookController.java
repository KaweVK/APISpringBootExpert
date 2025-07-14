package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.RegisterBookDTO;
import com.github.kawevk.libraryapi.mappers.BookMapper;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody @Valid RegisterBookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        bookService.createBook(book);
        var uri = generateLocation(book.getId());
        return ResponseEntity.created(uri).body("Author created successfully with ID: " + book.getId());
    }
}
