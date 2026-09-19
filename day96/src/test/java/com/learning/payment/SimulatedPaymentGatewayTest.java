package com.learning.payment;

import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

class SimulatedPaymentGatewayTest {

    private final SimulatedPaymentGateway gateway = new SimulatedPaymentGateway();
    private final YearMonth future = YearMonth.now().plusYears(2);

    @Test
    void approvesAValidTestCard() {
        PaymentResult result = gateway.charge(new PaymentRequest(1000, "4242424242424242", future.getMonthValue(), future.getYear(), "123"));

        assertTrue(result.approved());
        assertNotNull(result.transactionId());
    }

    @Test
    void declinesTheReservedAlwaysDeclineTestCard() {
        PaymentResult result = gateway.charge(new PaymentRequest(1000, "4000000000000002", future.getMonthValue(), future.getYear(), "123"));

        assertFalse(result.approved());
        assertNull(result.transactionId());
        assertTrue(result.message().toLowerCase().contains("declined"));
    }

    @Test
    void declinesACardThatFailsTheLuhnChecksum() {
        PaymentResult result = gateway.charge(new PaymentRequest(1000, "1234567890123456", future.getMonthValue(), future.getYear(), "123"));

        assertFalse(result.approved());
    }

    @Test
    void declinesAnExpiredCard() {
        YearMonth past = YearMonth.now().minusYears(1);
        PaymentResult result = gateway.charge(new PaymentRequest(1000, "4242424242424242", past.getMonthValue(), past.getYear(), "123"));

        assertFalse(result.approved());
        assertTrue(result.message().toLowerCase().contains("expired"));
    }

    @Test
    void declinesANonPositiveAmount() {
        PaymentResult result = gateway.charge(new PaymentRequest(0, "4242424242424242", future.getMonthValue(), future.getYear(), "123"));
        assertFalse(result.approved());
    }

    @Test
    void ignoresSpacesInTheCardNumber() {
        PaymentResult result = gateway.charge(new PaymentRequest(1000, "4242 4242 4242 4242", future.getMonthValue(), future.getYear(), "123"));
        assertTrue(result.approved());
    }
}
