package com.learning.controller;

import com.learning.model.Todo;
import com.learning.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void addingATodoThroughTheFormPersistsItAndShowsItOnTheHomePage() throws Exception {
        mockMvc.perform(post("/todos").param("title", "Buy milk"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Buy milk")));
    }

    @Test
    void togglingACompletedTodoMarksItDoneAndTheActiveFilterHidesIt() throws Exception {
        Todo todo = todoRepository.save(new Todo("Finish homework"));

        mockMvc.perform(post("/todos/" + todo.getId() + "/toggle").param("filter", "ALL"))
                .andExpect(status().is3xxRedirection());

        assertTrue(todoRepository.findById(todo.getId()).orElseThrow().isCompleted());

        mockMvc.perform(get("/").param("filter", "ACTIVE"))
                .andExpect(content().string(not(containsString("Finish homework"))));

        mockMvc.perform(get("/").param("filter", "COMPLETED"))
                .andExpect(content().string(containsString("Finish homework")));
    }

    @Test
    void deletingATodoRemovesItFromTheDatabaseAndThePage() throws Exception {
        Todo todo = todoRepository.save(new Todo("Temporary task"));

        mockMvc.perform(post("/todos/" + todo.getId() + "/delete").param("filter", "ALL"))
                .andExpect(status().is3xxRedirection());

        assertFalse(todoRepository.existsById(todo.getId()));

        mockMvc.perform(get("/"))
                .andExpect(content().string(not(containsString("Temporary task"))));
    }

    @Test
    void theActiveFilterOnlyShowsIncompleteTodos() throws Exception {
        todoRepository.save(new Todo("Active one"));
        Todo done = todoRepository.save(new Todo("Done one"));
        done.setCompleted(true);
        todoRepository.save(done);

        mockMvc.perform(get("/").param("filter", "ACTIVE"))
                .andExpect(content().string(containsString("Active one")))
                .andExpect(content().string(not(containsString("Done one"))));
    }

    @Test
    void togglingANonexistentTodoReturnsNotFound() throws Exception {
        mockMvc.perform(post("/todos/999999/toggle").param("filter", "ALL"))
                .andExpect(status().isNotFound());
    }
}
