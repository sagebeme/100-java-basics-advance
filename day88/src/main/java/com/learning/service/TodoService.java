package com.learning.service;

import com.learning.model.Todo;
import com.learning.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public static final int MAX_TITLE_LENGTH = 200;

    public Todo addTodo(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Todo title must not be blank");
        }
        if (title.trim().length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException("Keep a todo under " + MAX_TITLE_LENGTH + " characters");
        }
        return todoRepository.save(new Todo(title.trim()));
    }

    public List<Todo> listTodos(TodoFilter filter) {
        return switch (filter) {
            case ALL -> todoRepository.findAllByOrderByCreatedAtDesc();
            case ACTIVE -> todoRepository.findByCompletedOrderByCreatedAtDesc(false);
            case COMPLETED -> todoRepository.findByCompletedOrderByCreatedAtDesc(true);
        };
    }

    public Todo toggleComplete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No todo with id " + id));
        todo.setCompleted(!todo.isCompleted());
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new NoSuchElementException("No todo with id " + id);
        }
        todoRepository.deleteById(id);
    }
}
