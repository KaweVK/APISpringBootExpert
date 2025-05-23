package com.github.kawevk.carsapi.todos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {
    Boolean existsByDescription(String description);
}
