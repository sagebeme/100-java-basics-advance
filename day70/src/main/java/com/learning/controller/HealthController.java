package com.learning.controller;

import com.learning.model.Item;
import com.learning.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/items")
    public Item addItem(@RequestParam String name) {
        return itemRepository.save(new Item(name));
    }

    @GetMapping("/items")
    public List<Item> listItems() {
        return itemRepository.findAll();
    }
}
