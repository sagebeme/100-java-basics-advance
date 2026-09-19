package com.learning.config;

import com.learning.model.Product;
import com.learning.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) {
            return;
        }
        productRepository.save(new Product("Mechanical Keyboard", "Tactile switches, per-key backlighting", 8999, 25));
        productRepository.save(new Product("Wireless Mouse", "2.4GHz wireless, 1600 DPI", 2499, 50));
        productRepository.save(new Product("USB-C Hub", "7-in-1 with HDMI and card reader", 3499, 40));
        productRepository.save(new Product("27-inch Monitor", "1440p IPS, 144Hz", 24999, 15));
        productRepository.save(new Product("Laptop Stand", "Aluminum, adjustable height", 1999, 60));
    }
}
