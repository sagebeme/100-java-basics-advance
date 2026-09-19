package com.learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int priceCents;
    private int stock;

    protected Product() {
    }

    public Product(String name, String description, int priceCents, int stock) {
        this.name = name;
        this.description = description;
        this.priceCents = priceCents;
        this.stock = stock;
    }

    /** For tests that need a Product with a known id without persisting it through JPA. */
    public Product(Long id, String name, String description, int priceCents, int stock) {
        this(name, description, priceCents, stock);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public double getPriceDollars() {
        return priceCents / 100.0;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
