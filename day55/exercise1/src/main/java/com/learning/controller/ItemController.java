package com.learning.controller;

import com.learning.model.Item;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    // One controller serves every request, often at the same time, so the map must be safe to share.
    // A skip-list map is, and it keeps the ids in order too.
    private final Map<Long, Item> items = new ConcurrentSkipListMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    // Done, as an example: GET /api/items
    @GetMapping
    public List<Item> all() {
        return new ArrayList<>(items.values());
    }

    // TODO: GET /api/items/{id} → 200 and the item, or 404
    public ResponseEntity<Item> one(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    // TODO: POST /api/items → give it the next id, 201 Created, a Location header, and the item
    public ResponseEntity<Item> create(Item item) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    // TODO: PUT /api/items/{id} → change its name and price, 200 and the item; 404 if there's no such id
    public ResponseEntity<Item> replace(Long id, Item changes) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    // TODO: DELETE /api/items/{id} → 204 No Content, or 404 if it was never there
    public ResponseEntity<Void> delete(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
