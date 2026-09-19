package com.learning.controller;

import com.learning.model.Cart;
import com.learning.model.CustomerOrder;
import com.learning.payment.PaymentRequest;
import com.learning.service.OrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {

    private final OrderService orderService;

    public CheckoutController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String showCheckout(HttpSession session, Model model) {
        Cart cart = CartSessionSupport.getCart(session);
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        model.addAttribute("checkoutForm", new CheckoutForm());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String submitCheckout(@Valid @ModelAttribute CheckoutForm checkoutForm, BindingResult bindingResult,
                                  HttpSession session, Model model) {
        Cart cart = CartSessionSupport.getCart(session);
        if (bindingResult.hasErrors()) {
            model.addAttribute("cart", cart);
            return "checkout";
        }

        PaymentRequest paymentRequest = new PaymentRequest(cart.getTotalCents(), checkoutForm.getCardNumber(),
                checkoutForm.getExpiryMonth(), checkoutForm.getExpiryYear(), checkoutForm.getCvv());
        CustomerOrder order = orderService.checkout(cart, checkoutForm.getEmail(), paymentRequest);

        return "redirect:/orders/" + order.getId();
    }
}
