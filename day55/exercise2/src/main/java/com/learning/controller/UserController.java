package com.learning.controller;

import com.learning.exception.ResourceNotFoundException;
import com.learning.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Query-parameter search: both filters are optional, and either can be applied alone.
     */
    @GetMapping("/search")
    public List<User> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        return users.stream()
                .filter(u -> name == null || u.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(u -> email == null || u.getEmail().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return findOrThrow(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        user.setId(nextId.getAndIncrement());
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User updated) {
        User user = findOrThrow(id);
        user.setName(updated.getName());
        user.setEmail(updated.getEmail());
        return user;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        findOrThrow(id);
        users.removeIf(u -> u.getId().equals(id));
        return ResponseEntity.noContent().build();
    }

    private User findOrThrow(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No user with id " + id));
    }
}
