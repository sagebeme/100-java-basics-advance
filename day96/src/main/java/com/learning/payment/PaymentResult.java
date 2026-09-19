package com.learning.payment;

public record PaymentResult(boolean approved, String transactionId, String message) {
}
