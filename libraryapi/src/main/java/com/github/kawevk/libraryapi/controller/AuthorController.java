package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.AuthorDTO;
import com.github.kawevk.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity createAuthor(@RequestBody AuthorDTO authorDTO) {

        return new ResponseEntity("Author created successfully" + authorDTO, HttpStatus.CREATED);
    }


}
