package com.learning.service;

import com.learning.model.Cart;
import com.learning.model.CartItem;
import com.learning.model.CustomerOrder;
import com.learning.model.OrderLineItem;
import com.learning.model.OrderStatus;
import com.learning.payment.PaymentGateway;
import com.learning.payment.PaymentRequest;
import com.learning.payment.PaymentResult;
import com.learning.model.Product;
import com.learning.repository.OrderRepository;
import com.learning.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OrderService {

    private final PaymentGateway paymentGateway;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(PaymentGateway paymentGateway, OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.paymentGateway = paymentGateway;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Charges the cart's total through the payment gateway and records the outcome as an Order
     * either way - a declined payment is still a real order record, just with DECLINED status,
     * not silently dropped.
     */
    @Transactional
    public CustomerOrder checkout(Cart cart, String customerEmail, PaymentRequest cardDetails) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot check out an empty cart");
        }

        // The cart lives in the session and may be hours old: check today's stock in the
        // database before charging anyone, so we never take money for things we don't have.
        Map<CartItem, Product> inStock = new LinkedHashMap<>();
        for (CartItem item : cart.getItems().values()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new IllegalStateException(item.getProduct().getName() + " is no longer sold"));
            if (item.getQuantity() > product.getStock()) {
                throw new IllegalStateException("Only " + product.getStock() + " " + product.getName()
                        + " left. Please change the quantity in your cart.");
            }
            inStock.put(item, product);
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
            inStock.forEach((item, product) -> {
                product.setStock(product.getStock() - item.getQuantity());
                productRepository.save(product);
            });
            cart.clear();
        }

        return saved;
    }
}
