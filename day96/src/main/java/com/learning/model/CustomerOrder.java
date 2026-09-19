package com.learning.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Named CustomerOrder rather than Order since ORDER is a reserved SQL keyword (the same class of
 * H2 naming pitfall hit earlier in this course with USER/YEAR/RANK).
 */
@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;
    private int totalCents;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String transactionId;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLineItem> lineItems = new ArrayList<>();

    protected CustomerOrder() {
    }

    public CustomerOrder(String customerEmail, int totalCents, OrderStatus status, String transactionId) {
        this.customerEmail = customerEmail;
        this.totalCents = totalCents;
        this.status = status;
        this.transactionId = transactionId;
        this.createdAt = LocalDateTime.now();
    }

    public void addLineItem(OrderLineItem item) {
        lineItems.add(item);
        item.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getTotalCents() {
        return totalCents;
    }

    public double getTotalDollars() {
        return totalCents / 100.0;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }
}
