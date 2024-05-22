package com.finanzdata.taskservice.service;

import com.finanzdata.taskservice.domain.ToDo;
import com.finanzdata.taskservice.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> findAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDo saveTodo(ToDo todo) {
        return toDoRepository.save(todo);
    }

    public Optional<ToDo> findTodoById(Long id) {
        return toDoRepository.findById(id);
    }

    public void deleteToDoById(Long id) {
        toDoRepository.deleteById(id);
    }


    public List<ToDo> getTodosByStatus(String status) {
        return toDoRepository.findByStatus(status);
    }

    public List<ToDo> getTodosCompletionDateBefore(LocalDate date) {
        return toDoRepository.findAllWithCompletionDateBefore(date);
    }
}
