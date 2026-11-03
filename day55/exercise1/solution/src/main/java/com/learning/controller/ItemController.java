package com.learning.controller;

import com.learning.model.Item;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
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

    @GetMapping
    public List<Item> all() {
        return new ArrayList<>(items.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> one(@PathVariable Long id) {
        Item item = items.get(id);
        return item == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        item.setId(nextId.getAndIncrement());
        items.put(item.getId(), item);
        return ResponseEntity.created(URI.create("/api/items/" + item.getId())).body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> replace(@PathVariable Long id, @RequestBody Item changes) {
        Item item = items.get(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        item.setName(changes.getName());
        item.setShillings(changes.getShillings());
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return items.remove(id) == null ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
}
