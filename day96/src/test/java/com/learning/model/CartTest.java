package com.learning.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Product product(long id, int priceCents) {
        return new Product(id, "Widget", "desc", priceCents, 100);
    }

    @Test
    void addingANewProductCreatesACartItem() {
        Cart cart = new Cart();
        Product p = product(1, 500);

        cart.addItem(p, 2);

        assertEquals(1, cart.getItems().size());
        assertEquals(1000, cart.getTotalCents());
        assertEquals(2, cart.getItemCount());
    }

    @Test
    void addingTheSameProductTwiceAccumulatesQuantity() {
        Cart cart = new Cart();
        Product p = product(1, 500);

        cart.addItem(p, 2);
        cart.addItem(p, 3);

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().values().iterator().next().getQuantity());
        assertEquals(2500, cart.getTotalCents());
    }

    @Test
    void addingAZeroOrNegativeQuantityThrows() {
        Cart cart = new Cart();
        Product p = product(1, 500);

        assertThrows(IllegalArgumentException.class, () -> cart.addItem(p, 0));
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(p, -1));
    }

    @Test
    void updatingQuantityToZeroRemovesTheItem() {
        Cart cart = new Cart();
        Product p = product(1, 500);
        cart.addItem(p, 2);

        cart.updateQuantity(p.getId(), 0);

        assertTrue(cart.isEmpty());
    }

    @Test
    void removingAnItemDropsItFromTheCart() {
        Cart cart = new Cart();
        Product p = product(1, 500);
        cart.addItem(p, 2);

        cart.removeItem(p.getId());

        assertTrue(cart.isEmpty());
    }

    @Test
    void clearEmptiesTheWholeCart() {
        Cart cart = new Cart();
        cart.addItem(product(1, 500), 1);
        cart.addItem(product(2, 300), 1);

        cart.clear();

        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getTotalCents());
    }
}
