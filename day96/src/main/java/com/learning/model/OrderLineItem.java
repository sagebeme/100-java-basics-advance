package com.learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int unitPriceCents;
    private int quantity;

    @ManyToOne
    private CustomerOrder order;

    protected OrderLineItem() {
    }

    public OrderLineItem(String productName, int unitPriceCents, int quantity) {
        this.productName = productName;
        this.unitPriceCents = unitPriceCents;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitPriceCents() {
        return unitPriceCents;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubtotalCents() {
        return unitPriceCents * quantity;
    }

    void setOrder(CustomerOrder order) {
        this.order = order;
    }
}
