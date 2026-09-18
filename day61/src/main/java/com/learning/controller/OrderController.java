package com.learning.controller;

import com.learning.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute Order order) {
        orders.add(order);
        return "redirect:/orders";
    }
}
