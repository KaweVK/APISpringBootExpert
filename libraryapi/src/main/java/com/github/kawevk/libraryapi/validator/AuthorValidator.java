package com.github.kawevk.libraryapi.validator;

import com.github.kawevk.libraryapi.exception.DuplicatedRegisterException;
import com.github.kawevk.libraryapi.model.Author;
import com.github.kawevk.libraryapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidator {

    private final AuthorRepository authorRepository;

    public void validate(Author author){
        if(existAuthor(author)){
            throw new DuplicatedRegisterException("Author alredy exists");
        }
    }

    private boolean existAuthor(Author author) {
        Optional<Author> authorFinded = authorRepository.findByNationalityAndNameAndBirthDate(author.getNationality(), author.getName(), author.getBirthDate()
        );

        if (author.getId() == null) {
            return authorFinded.isPresent();
        }

        return authorFinded.isPresent() && !author.getId().equals(authorFinded.get().getId());
    }

}
