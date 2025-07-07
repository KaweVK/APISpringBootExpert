package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.AuthorDTO;
import com.github.kawevk.libraryapi.dto.ErrorAnswer;
import com.github.kawevk.libraryapi.exception.DuplicatedRegisterException;
import com.github.kawevk.libraryapi.exception.OperationNotAllowedException;
import com.github.kawevk.libraryapi.mappers.AuthorMapper;
import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> createAuthor(@RequestBody @Valid AuthorDTO authorDTO) {
        try {
            var author = mapper.toEntity(authorDTO);
            authorService.createAuthor(author);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(author.getId())
                    .toUri();

            return ResponseEntity.created(location)
                    .body("Author created successfully with ID: " + author.getId());
        } catch (DuplicatedRegisterException e) {
            var errorDTO = ErrorAnswer.conflictAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        } catch (Exception e) {
            var errorDTO = ErrorAnswer.internalServerErrorAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);

        return authorService.findById(idAuthor).map(author -> {
            AuthorDTO dto = mapper.toDTO(author);
            return ResponseEntity.ok(dto);
        }).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") String id) {
        try {
            var idAuthor = UUID.fromString(id);
            Optional<Author> authorOptional = authorService.findById(idAuthor);
            if (authorOptional.isPresent()) {
                authorService.deleteAuthor(idAuthor);
                return ResponseEntity.ok("Author deleted successfully.");
            }
            return ResponseEntity.notFound().build();
        } catch (OperationNotAllowedException | IllegalArgumentException e) {
            var errorDTO = ErrorAnswer.defaultAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        } catch (Exception e) {
            var errorDTO = ErrorAnswer.internalServerErrorAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
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
        try {

            var idAuthor = UUID.fromString(id);
            Optional<Author> authorOptional = authorService.findById(idAuthor);
            if (authorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();

            }
            var author = authorOptional.get();
            authorService.updateAuthor(author);
            author.setName(authorDTO.name());
            author.setBirthDate(authorDTO.birthDate());
            author.setNationality(authorDTO.nationality());
            authorService.updateAuthor(author);

            return ResponseEntity.noContent().build();

        } catch (DuplicatedRegisterException e) {
            var errorDTO = ErrorAnswer.conflictAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        } catch (IllegalArgumentException e) {
            var errorDTO = ErrorAnswer.defaultAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        } catch (Exception e) {
            var errorDTO = ErrorAnswer.internalServerErrorAnswer(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

}
