package com.learning.controller;

import com.learning.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cart", CartSessionSupport.getCart(session));
        return "cart";
    }

    @PostMapping("/cart/update/{productId}")
    public String updateQuantity(@PathVariable Long productId, @RequestParam int quantity, HttpSession session) {
        CartSessionSupport.getCart(session).updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{productId}")
    public String removeItem(@PathVariable Long productId, HttpSession session) {
        CartSessionSupport.getCart(session).removeItem(productId);
        return "redirect:/cart";
    }
}
