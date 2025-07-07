package com.github.kawevk.libraryapi.mappers;

import com.github.kawevk.libraryapi.dto.AuthorDTO;
import com.github.kawevk.libraryapi.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    //@Mapping(source = "authorID", target = "id")
    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
