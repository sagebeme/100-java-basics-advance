package com.learning.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer = new Customer();
    private List<OrderItem> items = new ArrayList<>();

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}
