package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.RegisterBookDTO;
import com.github.kawevk.libraryapi.dto.SearchBookDTO;
import com.github.kawevk.libraryapi.mappers.BookMapper;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<SearchBookDTO> getBook(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        return bookService.findById(uuid).map(book -> {
            var dto = bookMapper.toDTO(book);
            return ResponseEntity.ok(dto);
        }).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") String id) {
        var uuid = UUID.fromString(id);
        return bookService.findById(uuid).map(book -> {
            bookService.deleteBook(book);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
