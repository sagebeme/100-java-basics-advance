package com.learning.controller;

import com.learning.exception.DuplicateProductException;
import com.learning.exception.ProductNotFoundException;
import com.learning.model.Product;
import com.learning.model.ProductPatch;
import com.learning.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// One resource, products, at one versioned path. The method says what to do with it:
//   GET    /api/v1/products        list              200
//   GET    /api/v1/products/{id}   one               200, or 404
//   POST   /api/v1/products        create            201 + Location, 409 if the name is taken
//   PUT    /api/v1/products/{id}   replace it whole  200
//   PATCH  /api/v1/products/{id}   change some fields 200
//   DELETE /api/v1/products/{id}   remove            204, or 404
@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(find(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        if (productRepository.existsByNameIgnoreCase(product.getName())) {
            throw new DuplicateProductException(product.getName());
        }
        Product saved = productRepository.save(product);
        // 201 says "I made it"; Location says where it lives now.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // PUT replaces the whole product: send every field. Sending the same PUT twice leaves the same result (idempotent).
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updated) {
        Product product = find(id);
        rejectNameTakenByAnother(updated.getName(), id);
        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        return ResponseEntity.ok(productRepository.save(product));
    }

    // PATCH changes only the fields you send. { "price": 300 } leaves the name alone.
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @Valid @RequestBody ProductPatch patch) {
        Product product = find(id);
        if (patch.name() != null) {
            rejectNameTakenByAnother(patch.name(), id);
            product.setName(patch.name());
        }
        if (patch.price() != null) {
            product.setPrice(patch.price());
        }
        return ResponseEntity.ok(productRepository.save(product));
    }

    // 204: done, nothing to send back. A second DELETE gets 404, but the product is just as gone: still idempotent.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Product find(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    private void rejectNameTakenByAnother(String name, Long id) {
        productRepository.findByNameIgnoreCase(name)
                .filter(other -> !other.getId().equals(id))
                .ifPresent(other -> {
                    throw new DuplicateProductException(name);
                });
    }
}
