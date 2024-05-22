package com.finanzdata.taskservice.controller;



import com.finanzdata.taskservice.domain.ToDo;
import com.finanzdata.taskservice.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping
    public ResponseEntity<List<ToDo>> getAllTodos() {
        List<ToDo> todos = toDoRepository.findAll();
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable Long id) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        return todo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<ToDo> createTodo(@RequestBody ToDo todo) {
        ToDo savedTodo = toDoRepository.save(todo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTodo.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable Long id, @RequestBody ToDo todo) {
        return toDoRepository.findById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(todo.getTitle());
                    existingTodo.setDescription(todo.getDescription());
                    existingTodo.setCompletionDate(todo.getCompletionDate());

                    toDoRepository.save(existingTodo);
                    return ResponseEntity.ok(existingTodo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        return toDoRepository.findById(id)
                .map(todo -> {
                    toDoRepository.delete(todo);
                    return ResponseEntity.noContent().<Void>build(); // Explicitly specify type for clarity
                })
                .orElseGet(() -> ResponseEntity.<Void>notFound().build()); // Ensure proper type is specified
    }
}
