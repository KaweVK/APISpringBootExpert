package com.github.kawevk.carsapi.todos;

import org.springframework.stereotype.Component;

@Component
public class ToDoValidator {

    private ToDoRepository toDoRepository;

    public ToDoValidator(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public void validar(ToDoEntity toDoEntity) {
        if (existByDescription(toDoEntity.getDescription())) {
            throw new IllegalArgumentException("ToDo with this description already exists");
        }

    }

    private Boolean existByDescription(String description) {
        return toDoRepository.existsByDescription(description);
    }

}
