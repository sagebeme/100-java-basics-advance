package com.learning.controller;

import com.learning.model.CustomerOrder;
import com.learning.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /** Only the orders placed in this browser session: other people's orders aren't ours to show. */
    @GetMapping("/orders")
    public String listOrders(HttpSession session, Model model) {
        List<CustomerOrder> mine = orderRepository.findAllById(CartSessionSupport.getMyOrderIds(session)).stream()
                .sorted(Comparator.comparing(CustomerOrder::getCreatedAt).reversed())
                .toList();
        model.addAttribute("orders", mine);
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetail(@PathVariable Long id, HttpSession session, Model model) {
        // Someone else's order answers exactly like a missing one, so ids can't be probed.
        if (!CartSessionSupport.getMyOrderIds(session).contains(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with id " + id);
        }
        model.addAttribute("order", orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with id " + id)));
        return "order-detail";
    }
}
