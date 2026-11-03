package com.learning.controller;

import com.learning.model.Order;
import com.learning.model.OrderItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

// One order: a customer (an object inside the order) and a list of items, all from one form.
@Controller
public class OrderController {

    static final int ITEM_ROWS = 3;

    private final List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    @GetMapping("/orders")
    public String form(Model model) {
        if (!model.containsAttribute("order")) {
            model.addAttribute("order", blankOrder());
        }
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute Order order, BindingResult result, Model model, RedirectAttributes redirect) {
        // Rows the customer left empty aren't items.
        order.getItems().removeIf(item -> item.getProduct() == null || item.getProduct().isBlank());
        if (result.hasErrors() || order.getItems().isEmpty()) {
            model.addAttribute("error", result.hasErrors()
                    ? "Each quantity must be a whole number."
                    : "Add at least one item.");
            while (order.getItems().size() < ITEM_ROWS) {
                order.getItems().add(new OrderItem());
            }
            model.addAttribute("orders", orders);
            return "orders";
        }
        orders.add(order);
        redirect.addFlashAttribute("message", "Order for " + order.getCustomer().getName() + " saved.");
        return "redirect:/orders";
    }

    private static Order blankOrder() {
        Order order = new Order();
        for (int i = 0; i < ITEM_ROWS; i++) {
            OrderItem item = new OrderItem();
            item.setQuantity(1);
            order.getItems().add(item);
        }
        return order;
    }
}
