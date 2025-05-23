package com.github.kawevk.carsapi.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    @Autowired //com construtor não precisa
    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDoEntity create(ToDoEntity toDoEntity) {
        return toDoRepository.save(toDoEntity);
    }

    public void updateStatus(ToDoEntity toDoEntity) {
        toDoRepository.save(toDoEntity);
    }

    public List<ToDoEntity> findAll() {
        return toDoRepository.findAll();
    }
}
