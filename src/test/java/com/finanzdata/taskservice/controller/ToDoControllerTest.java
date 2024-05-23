package com.finanzdata.taskservice.controller;

import com.finanzdata.taskservice.domain.ToDo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import com.finanzdata.taskservice.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ToDoControllerTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoController toDoController;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void getAllTodos_WhenNoToDos_ReturnsNoContent() {
        when(toDoRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<ToDo>> response = toDoController.getAllTodos();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "Expected status to be 204 NO_CONTENT");
        assertNull(response.getBody(), "Expected body to be null when there is no content");
    }

    @Test
    void getAllTodos_WhenToDosExist_ReturnsTodos() {
        ToDo toDo = new ToDo(1L, "Test Task", "Complete the test", LocalDate.now());
        when(toDoRepository.findAll()).thenReturn(Arrays.asList(toDo));
        ResponseEntity<List<ToDo>> response = toDoController.getAllTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(toDo, response.getBody().get(0));
    }

    @Test
    void getTodoById_WhenToDoExists_ReturnsTodo() {
        ToDo toDo = new ToDo(1L, "Test Task", "Complete the test", LocalDate.now());
        when(toDoRepository.findById(1L)).thenReturn(Optional.of(toDo));
        ResponseEntity<ToDo> response = toDoController.getTodoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(toDo, response.getBody());
    }

    @Test
    void getTodoById_WhenToDoDoesNotExist_ReturnsNotFound() {
        when(toDoRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<ToDo> response = toDoController.getTodoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createTodo_CreatesAndReturnsToDo() {
        ToDo toDo = new ToDo(null, "New Task", "Details of the new task", LocalDate.now());
        ToDo savedTodo = new ToDo(1L, "New Task", "Details of the new task", LocalDate.now());
        when(toDoRepository.save(toDo)).thenReturn(savedTodo);

        ResponseEntity<ToDo> response = toDoController.createTodo(toDo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedTodo.getId(), response.getBody().getId());
    }

    @Test
    void updateTodo_WhenExists_UpdatesAndReturnsToDo() {
        ToDo existingToDo = new ToDo(1L, "Existing Task", "Before update", LocalDate.now());
        ToDo updatedToDo = new ToDo(1L, "Existing Task", "After update", LocalDate.now());
        when(toDoRepository.findById(1L)).thenReturn(Optional.of(existingToDo));
        when(toDoRepository.save(existingToDo)).thenReturn(updatedToDo);

        ResponseEntity<ToDo> response = toDoController.updateTodo(1L, updatedToDo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(toDoRepository).save(existingToDo);
        assertEquals("After update", response.getBody().getDescription());
    }

    @Test
    void deleteToDo_WhenExists_DeletesAndReturnsNoContent() {
        ToDo toDoToDelete = new ToDo(1L, "Task to delete", "Will be deleted", LocalDate.now());
        when(toDoRepository.findById(1L)).thenReturn(Optional.of(toDoToDelete));
        doNothing().when(toDoRepository).delete(toDoToDelete);

        ResponseEntity<Void> response = toDoController.deleteToDo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(toDoRepository).delete(toDoToDelete);
    }
}

