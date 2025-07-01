package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.AuthorDTO;
import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorDTO authorDTO) {
        var author = authorDTO.MapToAuthor();
        authorService.createAuthor(author);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body("Author created successfully with ID: " + author.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = authorService.getAuthor(idAuthor);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            AuthorDTO dto = new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getBirthDate(),
                author.getNationality()
            );
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = authorService.getAuthor(idAuthor);
        if (authorOptional.isPresent()) {
            authorService.deleteAuthor(idAuthor);
            return ResponseEntity.ok("Author deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> searchAuthors(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nacionality", required = false) String nacionality
    ) {
        List<Author> authors = authorService.searchAuthors(name, nacionality);
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(author -> new AuthorDTO(
                        author.getId(),
                        author.getName(),
                        author.getBirthDate(),
                        author.getNationality()))
                .toList();
        return ResponseEntity.ok(authorDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable("id") String id, @RequestBody AuthorDTO authorDTO) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = authorService.getAuthor(idAuthor);
        if (authorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        var author = authorOptional.get();
        authorService.updateAuthor(author);
        author.setName(authorDTO.name());
        author.setBirthDate(authorDTO.birthDate());
        author.setNationality(authorDTO.nacionality());
        authorService.updateAuthor(author);

        return ResponseEntity.noContent().build();
    }

}
