package com.learning.controller;

import com.learning.model.Cart;
import jakarta.servlet.http.HttpSession;

import java.util.LinkedHashSet;
import java.util.Set;

class CartSessionSupport {

    private static final String SESSION_KEY = "cart";

    static Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(SESSION_KEY);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(SESSION_KEY, cart);
        }
        return cart;
    }

    private static final String ORDERS_KEY = "myOrders";

    /**
     * The ids of the orders placed in this browser session. There are no accounts in this shop,
     * so this is what stops one visitor from reading another's orders (and email address).
     */
    @SuppressWarnings("unchecked")
    static Set<Long> getMyOrderIds(HttpSession session) {
        Set<Long> ids = (Set<Long>) session.getAttribute(ORDERS_KEY);
        if (ids == null) {
            ids = new LinkedHashSet<>();
            session.setAttribute(ORDERS_KEY, ids);
        }
        return ids;
    }
}
