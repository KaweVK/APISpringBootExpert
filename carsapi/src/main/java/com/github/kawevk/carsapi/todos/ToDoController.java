package com.github.kawevk.carsapi.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/to-dos")
public class ToDoController {

    @Autowired //se tiver construtor não precisa
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public ToDoEntity create(@RequestBody ToDoEntity toDoEntity) {
        return toDoService.create(toDoEntity);
    }
}
