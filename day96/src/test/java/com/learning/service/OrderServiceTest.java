package com.learning.service;

import com.learning.model.Cart;
import com.learning.model.CustomerOrder;
import com.learning.model.OrderStatus;
import com.learning.model.Product;
import com.learning.payment.PaymentGateway;
import com.learning.payment.PaymentRequest;
import com.learning.payment.PaymentResult;
import com.learning.repository.OrderRepository;
import com.learning.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    private Product widget;

    private OrderService orderService;
    private Cart cart;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(paymentGateway, orderRepository, productRepository);
        widget = new Product(1L, "Widget", "desc", 500, 10);
        cart = new Cart();
        cart.addItem(widget, 2);
        lenient().when(productRepository.findById(1L)).thenReturn(Optional.of(widget));

        // lenient: the empty-cart test throws before ever reaching save(), which would otherwise
        // make Mockito's strict stubbing check flag this as an unnecessary stub for that one test.
        lenient().when(orderRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void anApprovedPaymentProducesAPaidOrderAndClearsTheCart() {
        when(paymentGateway.charge(any())).thenReturn(new PaymentResult(true, "tx-123", "Payment approved"));

        CustomerOrder order = orderService.checkout(cart, "buyer@example.com", new PaymentRequest(1000, "4242424242424242", 12, 2030, "123"));

        assertEquals(OrderStatus.PAID, order.getStatus());
        assertEquals("tx-123", order.getTransactionId());
        assertEquals(1000, order.getTotalCents());
        assertEquals(1, order.getLineItems().size());
        assertTrue(cart.isEmpty());
        assertEquals(8, widget.getStock(), "the 2 sold come off the stock");
    }

    @Test
    void aDeclinedPaymentProducesADeclinedOrderAndKeepsTheCartIntact() {
        when(paymentGateway.charge(any())).thenReturn(new PaymentResult(false, null, "Card was declined"));

        CustomerOrder order = orderService.checkout(cart, "buyer@example.com", new PaymentRequest(1000, "4000000000000002", 12, 2030, "123"));

        assertEquals(OrderStatus.DECLINED, order.getStatus());
        assertNull(order.getTransactionId());
        assertFalse(cart.isEmpty()); // declined checkout should not clear the cart
        assertEquals(10, widget.getStock(), "nothing was sold, so the stock is unchanged");
    }

    @Test
    void buyingMoreThanIsInStockIsRefusedBeforeAnyoneIsCharged() {
        widget.setStock(1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                orderService.checkout(cart, "buyer@example.com", new PaymentRequest(1000, "4242424242424242", 12, 2030, "123")));

        assertTrue(e.getMessage().contains("Only 1 Widget left"), e.getMessage());
        verify(paymentGateway, never()).charge(any());
        verify(orderRepository, never()).save(any());
    }

    @Test
    void checkingOutAnEmptyCartThrowsAndNeverChargesThePaymentGateway() {
        Cart emptyCart = new Cart();

        assertThrows(IllegalStateException.class, () ->
                orderService.checkout(emptyCart, "buyer@example.com", new PaymentRequest(0, "4242424242424242", 12, 2030, "123")));
        verify(paymentGateway, never()).charge(any());
    }

    @Test
    void theChargedAmountMatchesTheCartTotalRegardlessOfWhatThePaymentRequestSaid() {
        when(paymentGateway.charge(any())).thenReturn(new PaymentResult(true, "tx-1", "ok"));
        int cartTotalBeforeCheckout = cart.getTotalCents(); // checkout clears the cart on approval

        orderService.checkout(cart, "buyer@example.com", new PaymentRequest(999999, "4242424242424242", 12, 2030, "123"));

        verify(paymentGateway).charge(argThat(req -> req.amountCents() == cartTotalBeforeCheckout));
        assertEquals(1000, cartTotalBeforeCheckout);
    }
}
