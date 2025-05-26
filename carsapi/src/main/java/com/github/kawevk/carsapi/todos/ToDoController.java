package com.github.kawevk.carsapi.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/to-dos")
public class ToDoController {

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public ToDoEntity create(@RequestBody ToDoEntity toDoEntity) {
        try {
            return toDoService.create(toDoEntity);
        } catch (Exception e) {
            var message = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }
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
