package com.github.kawevk.libraryapi.controller;

import com.github.kawevk.libraryapi.dto.RegisterBookDTO;
import com.github.kawevk.libraryapi.dto.SearchBookDTO;
import com.github.kawevk.libraryapi.mappers.BookMapper;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.model.BookGender;
import com.github.kawevk.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping("/search")
    public ResponseEntity<Page<SearchBookDTO>> search(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "title", required = false)
            String title,
            @RequestParam(value = "author-name", required = false)
            String authorName,
            @RequestParam(value = "book-gender", required = false)
            BookGender bookGender,
            @RequestParam(value = "publication-year", required = false)
            Integer publicationYear,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer size
    ) {
       Page<Book> pageResult = bookService.search(isbn, title, authorName, bookGender, publicationYear, page, size);

       Page<SearchBookDTO> result = pageResult.map(bookMapper::toDTO);

       return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") String id, @RequestBody @Valid RegisterBookDTO bookDTO) {
        return bookService.findById(UUID.fromString(id))
                .map(book -> {
                    Book entity = bookMapper.toEntity(bookDTO);

                    book.setIsbn(entity.getIsbn());
                    book.setTitle(entity.getTitle());
                    book.setAuthor(entity.getAuthor());
                    book.setPrice(entity.getPrice());
                    book.setPublicationDate(entity.getPublicationDate());
                    book.setGender(entity.getGender());

                    bookService.updateBook(book);

                    return ResponseEntity.noContent().build();

                }).orElseGet( () -> ResponseEntity.notFound().build());
    }
}
