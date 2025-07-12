package com.github.kawevk.libraryapi.mappers;

import com.github.kawevk.libraryapi.dto.RegisterBookDTO;
import com.github.kawevk.libraryapi.model.Book;
import com.github.kawevk.libraryapi.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java( authorRepository.findById(bookDTO.authorId()).orElse(null) )")
    @Mapping(target = "publicationDate", source = "publicationDate")
    public abstract Book toEntity(RegisterBookDTO bookDTO);

}
