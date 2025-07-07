package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.ErrorAnswer;
import com.github.kawevk.libraryapi.dto.RegisterBookDTO;
import com.github.kawevk.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody @Valid RegisterBookDTO book) {
       try {
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            var errorDTO = ErrorAnswer.conflictAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }
}
