package com.github.kawevk.carsapi.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    @Autowired //com construtor não precisa
    private ToDoRepository toDoRepository;
    private ToDoValidator toDoValidator;
    private MailSender mailSender;

    public ToDoService(ToDoRepository toDoRepository, ToDoValidator toDoValidator, MailSender mailSender) {
        this.toDoRepository = toDoRepository;
        this.toDoValidator = toDoValidator;
        this.mailSender = mailSender;
    }

    public ToDoEntity create(ToDoEntity toDoEntity) {
        toDoValidator.validar(toDoEntity);
        return toDoRepository.save(toDoEntity);
    }

    public void updateStatus(ToDoEntity toDoEntity) {
        toDoRepository.save(toDoEntity);
        String content = toDoEntity.getCompleted() ? "completed" : "not completed";
        mailSender.sendMail("victorkawe07@gmail.com", "ToDo Status Updated", "The status of your ToDo has been updated to " + content);
    }

    public List<ToDoEntity> findAll() {
        return toDoRepository.findAll();
    }
}
