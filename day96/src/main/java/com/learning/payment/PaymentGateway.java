package com.learning.payment;

/**
 * Abstraction over a card payment processor. A real deployment would implement this with the
 * Stripe or PayPal SDK, which needs a merchant account and API credentials this environment
 * doesn't have - see SimulatedPaymentGateway for what stands in for it here.
 */
public interface PaymentGateway {
    PaymentResult charge(PaymentRequest request);
}
