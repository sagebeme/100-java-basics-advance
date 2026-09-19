package com.learning.controller;

import com.learning.model.Cart;
import com.learning.model.Product;
import com.learning.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

@Controller
public class CatalogController {

    private final ProductRepository productRepository;

    public CatalogController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String catalog(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "catalog";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId, @RequestParam(defaultValue = "1") int quantity, HttpSession session) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("No product with id " + productId));
        Cart cart = CartSessionSupport.getCart(session);
        cart.addItem(product, quantity);
        return "redirect:/cart";
    }
}
