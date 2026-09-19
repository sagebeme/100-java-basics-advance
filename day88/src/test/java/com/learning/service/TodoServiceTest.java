package com.learning.service;

import com.learning.model.Todo;
import com.learning.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoService(todoRepository);
    }

    @Test
    void addingATodoTrimsAndSavesTheTitle() {
        when(todoRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Todo saved = todoService.addTodo("  Buy milk  ");

        ArgumentCaptor<Todo> captor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository).save(captor.capture());
        assertEquals("Buy milk", captor.getValue().getTitle());
        assertEquals("Buy milk", saved.getTitle());
        assertFalse(saved.isCompleted());
    }

    @Test
    void addingABlankTodoThrows() {
        assertThrows(IllegalArgumentException.class, () -> todoService.addTodo("   "));
        assertThrows(IllegalArgumentException.class, () -> todoService.addTodo(null));
    }

    @Test
    void listingWithAllFilterReturnsEverythingNewestFirst() {
        List<Todo> todos = List.of(new Todo("first"), new Todo("second"));
        when(todoRepository.findAllByOrderByCreatedAtDesc()).thenReturn(todos);

        assertEquals(todos, todoService.listTodos(TodoFilter.ALL));
    }

    @Test
    void listingWithActiveFilterDelegatesToTheIncompleteQuery() {
        List<Todo> incomplete = List.of(new Todo("todo"));
        when(todoRepository.findByCompletedOrderByCreatedAtDesc(false)).thenReturn(incomplete);

        assertEquals(incomplete, todoService.listTodos(TodoFilter.ACTIVE));
    }

    @Test
    void listingWithCompletedFilterDelegatesToTheCompleteQuery() {
        List<Todo> completed = List.of(new Todo("done"));
        when(todoRepository.findByCompletedOrderByCreatedAtDesc(true)).thenReturn(completed);

        assertEquals(completed, todoService.listTodos(TodoFilter.COMPLETED));
    }

    @Test
    void togglingFlipsTheCompletedFlag() {
        Todo todo = new Todo("task");
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        when(todoRepository.save(todo)).thenReturn(todo);

        Todo result = todoService.toggleComplete(1L);
        assertTrue(result.isCompleted());

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        Todo resultAgain = todoService.toggleComplete(1L);
        assertFalse(resultAgain.isCompleted());
    }

    @Test
    void togglingAMissingTodoThrows() {
        when(todoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> todoService.toggleComplete(99L));
    }

    @Test
    void deletingAnExistingTodoRemovesIt() {
        when(todoRepository.existsById(1L)).thenReturn(true);

        todoService.deleteTodo(1L);

        verify(todoRepository).deleteById(1L);
    }

    @Test
    void deletingAMissingTodoThrowsAndNeverCallsDelete() {
        when(todoRepository.existsById(99L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> todoService.deleteTodo(99L));
        verify(todoRepository, never()).deleteById(any());
    }
}
