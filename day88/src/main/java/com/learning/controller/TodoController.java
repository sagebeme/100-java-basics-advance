package com.learning.controller;

import com.learning.service.TodoFilter;
import com.learning.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String list(@RequestParam(defaultValue = "ALL") TodoFilter filter, Model model) {
        model.addAttribute("todos", todoService.listTodos(filter));
        model.addAttribute("filter", filter);
        return "index";
    }

    @PostMapping("/todos")
    public String add(@RequestParam String title) {
        todoService.addTodo(title);
        return "redirect:/";
    }

    @PostMapping("/todos/{id}/toggle")
    public String toggle(@PathVariable Long id, @RequestParam(defaultValue = "ALL") TodoFilter filter) {
        todoService.toggleComplete(id);
        return "redirect:/?filter=" + filter;
    }

    @PostMapping("/todos/{id}/delete")
    public String delete(@PathVariable Long id, @RequestParam(defaultValue = "ALL") TodoFilter filter) {
        todoService.deleteTodo(id);
        return "redirect:/?filter=" + filter;
    }
}
