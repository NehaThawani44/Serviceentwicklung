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
        List<ToDo> toDos = toDoRepository.findAll();
        if (toDos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(toDos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        return toDo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<ToDo> createTodo(@RequestBody ToDo toDo) {
        ToDo savedTodo = toDoRepository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTodo.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable Long id, @RequestBody ToDo toDo) {
        return toDoRepository.findById(id)
                .map(existingTodo -> {
                    existingTodo.setTitle(toDo.getTitle());
                    existingTodo.setDescription(toDo.getDescription());
                    existingTodo.setCompletionDate(toDo.getCompletionDate());

                    toDoRepository.save(existingTodo);
                    return ResponseEntity.ok(existingTodo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        return toDoRepository.findById(id)
                .map(toDo -> {
                    toDoRepository.delete(toDo);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.<Void>notFound().build());
    }
}
