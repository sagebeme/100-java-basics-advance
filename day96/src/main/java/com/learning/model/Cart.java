package com.learning.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Stored as a session attribute (see CartController) rather than persisted - a cart isn't a
 * business record until it becomes an Order.
 */
public class Cart implements Serializable {

    private final Map<Long, CartItem> items = new LinkedHashMap<>();

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        CartItem existing = items.get(product.getId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    public void updateQuantity(Long productId, int quantity) {
        if (quantity <= 0) {
            items.remove(productId);
            return;
        }
        CartItem item = items.get(productId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public void removeItem(Long productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getTotalCents() {
        return items.values().stream().mapToInt(CartItem::getSubtotalCents).sum();
    }

    public double getTotalDollars() {
        return getTotalCents() / 100.0;
    }

    public int getItemCount() {
        return items.values().stream().mapToInt(CartItem::getQuantity).sum();
    }
}
