package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.AuthorDTO;
import com.github.kawevk.libraryapi.mappers.AuthorMapper;
import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController {

    private final AuthorService authorService;
    private final AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> createAuthor(@RequestBody @Valid AuthorDTO authorDTO) {
        var author = mapper.toEntity(authorDTO);
        authorService.createAuthor(author);

        URI location = generateLocation(author.getId());

        return ResponseEntity.created(location)
                .body("Author created successfully with ID: " + author.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);

        return authorService.findById(idAuthor).map(author -> {
            AuthorDTO dto = mapper.toDTO(author);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = authorService.findById(idAuthor);
        if (authorOptional.isPresent()) {
            authorService.deleteAuthor(idAuthor);
            return ResponseEntity.ok("Author deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> searchAuthors(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality
    ) {
        List<Author> authors = authorService.searchAuthorsByExample(name, nationality);
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(mapper::toDTO)
                .toList();
        return ResponseEntity.ok(authorDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") String id, @RequestBody @Valid AuthorDTO authorDTO) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = authorService.findById(idAuthor);
        if (authorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        var author = authorOptional.get();
        author.setName(authorDTO.name());
        author.setBirthDate(authorDTO.birthDate());
        author.setNationality(authorDTO.nationality());
        authorService.updateAuthor(author);

        return ResponseEntity.noContent().build();
    }

}
