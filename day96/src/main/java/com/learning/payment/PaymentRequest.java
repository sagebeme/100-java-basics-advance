package com.learning.payment;

public record PaymentRequest(int amountCents, String cardNumber, int expiryMonth, int expiryYear, String cvv) {
}
