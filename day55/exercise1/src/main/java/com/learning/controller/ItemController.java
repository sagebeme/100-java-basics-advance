package com.learning.controller;

import com.learning.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final List<Item> items = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @GetMapping
    public List<Item> getAllItems() {
        return items;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        item.setId(nextId.getAndIncrement());
        items.add(item);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updated) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                item.setName(updated.getName());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean removed = items.removeIf(item -> item.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
