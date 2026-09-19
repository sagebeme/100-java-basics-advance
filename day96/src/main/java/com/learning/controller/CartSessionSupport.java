package com.learning.controller;

import com.learning.model.Cart;
import jakarta.servlet.http.HttpSession;

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
}
