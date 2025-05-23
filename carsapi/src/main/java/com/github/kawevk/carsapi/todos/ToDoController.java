package com.github.kawevk.carsapi.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public void updateStatus(@RequestBody ToDoEntity toDoEntity) {
        toDoService.updateStatus(toDoEntity);
    }

    @GetMapping
    public List<ToDoEntity> findAll() {
        return toDoService.findAll();
    }
}
