package com.finanzdata.taskservice.service;

import com.finanzdata.taskservice.domain.ToDo;
import com.finanzdata.taskservice.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @Test
    void findAllToDos_ReturnsAllTodos() {
        // Setup
        ToDo todo1 = new ToDo(1L, "Task 1", "Description 1", LocalDate.now());
        ToDo todo2 = new ToDo(2L, "Task 2", "Description 2", LocalDate.now());
        when(toDoRepository.findAll()).thenReturn(Arrays.asList(todo1, todo2));

        // Execution
        List<ToDo> todos = toDoService.findAllToDos();

        // Verify
        assertEquals(2, todos.size());
        verify(toDoRepository).findAll();
    }

    @Test
    void findTodoById_WhenTodoExists_ReturnsTodo() {

        Optional<ToDo> expectedTodo = Optional.of(new ToDo(1L, "Task 1", "Description 1", LocalDate.now()));
        when(toDoRepository.findById(1L)).thenReturn(expectedTodo);


        Optional<ToDo> actualTodo = toDoService.findTodoById(1L);


        assertTrue(actualTodo.isPresent());
        assertEquals(expectedTodo.get().getId(), actualTodo.get().getId());
        verify(toDoRepository).findById(1L);
    }

    @Test
    void findTodoById_WhenTodoDoesNotExist_ReturnsEmpty() {

        when(toDoRepository.findById(1L)).thenReturn(Optional.empty());


        Optional<ToDo> todo = toDoService.findTodoById(1L);


        assertFalse(todo.isPresent());
        verify(toDoRepository).findById(1L);
    }

    @Test
    void saveTodo_SavesAndReturnsTodo() {

        ToDo toDo = new ToDo(null, "New Task", "Description", LocalDate.now());
        ToDo savedToDo = new ToDo(1L, "New Task", "Description", LocalDate.now());
        when(toDoRepository.save(toDo)).thenReturn(savedToDo);


        ToDo result = toDoService.saveTodo(toDo);


        assertNotNull(result);
        assertEquals(savedToDo.getId(), result.getId());
        verify(toDoRepository).save(toDo);
    }

    @Test
    void deleteTodoById_DeletesById() {

        toDoService.deleteToDoById(1L);

        
        verify(toDoRepository).deleteById(1L);
    }
}
