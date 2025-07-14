package com.github.kawevk.libraryapi.repository.specs;

import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.model.BookGender;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Book> authorNameLike(String authorName) {
        return (root, query, cb) -> {
            Join<Object, Object> joinAuthor = root.join("author", JoinType.LEFT);

            return cb.like(cb.upper(joinAuthor.get("name")), "%" + authorName.toUpperCase() + "%");
            // return cb.like( cb.upper(root.get("author").get("name")), "%" + authorName.toUpperCase() + "%");
        };
    }

    public static Specification<Book> bookGenderEqual(BookGender bookGender) {
        return (root, query, cb) -> cb.equal(root.get("gender"), bookGender);
    }

    public static Specification<Book> publicationYearEqual(Integer publicationYear) {
        // and to_char(publication_date, 'YYYY') = :publicationYear
        return (root, query, cb) -> cb.equal( cb.function("to_char", String.class, root.get("publicationDate"), cb.literal("YYYY")), publicationYear.toString());
    }


}
