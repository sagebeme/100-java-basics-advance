package com.learning.service;

import com.learning.model.Cart;
import com.learning.model.CartItem;
import com.learning.model.CustomerOrder;
import com.learning.model.OrderLineItem;
import com.learning.model.OrderStatus;
import com.learning.payment.PaymentGateway;
import com.learning.payment.PaymentRequest;
import com.learning.payment.PaymentResult;
import com.learning.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentGateway paymentGateway;
    private final OrderRepository orderRepository;

    public OrderService(PaymentGateway paymentGateway, OrderRepository orderRepository) {
        this.paymentGateway = paymentGateway;
        this.orderRepository = orderRepository;
    }

    /**
     * Charges the cart's total through the payment gateway and records the outcome as an Order
     * either way - a declined payment is still a real order record, just with DECLINED status,
     * not silently dropped.
     */
    public CustomerOrder checkout(Cart cart, String customerEmail, PaymentRequest cardDetails) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot check out an empty cart");
        }

        PaymentRequest chargeRequest = new PaymentRequest(cart.getTotalCents(), cardDetails.cardNumber(),
                cardDetails.expiryMonth(), cardDetails.expiryYear(), cardDetails.cvv());
        PaymentResult result = paymentGateway.charge(chargeRequest);

        CustomerOrder order = new CustomerOrder(customerEmail, cart.getTotalCents(),
                result.approved() ? OrderStatus.PAID : OrderStatus.DECLINED, result.transactionId());

        for (CartItem item : cart.getItems().values()) {
            order.addLineItem(new OrderLineItem(item.getProduct().getName(), item.getProduct().getPriceCents(), item.getQuantity()));
        }

        CustomerOrder saved = orderRepository.save(order);

        if (result.approved()) {
            cart.clear();
        }

        return saved;
    }
}
