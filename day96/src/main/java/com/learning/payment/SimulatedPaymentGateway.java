package com.learning.payment;

import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.UUID;

/**
 * Stands in for a real Stripe/PayPal integration, which needs a merchant account and API
 * credentials this environment doesn't have. It performs the same real, non-simulated checks a
 * gateway's client-side validation would (Luhn checksum, expiry date), and then follows the same
 * test-card convention real sandboxes use (e.g. Stripe's test cards): any number that passes Luhn
 * is approved UNLESS it ends in the reserved test suffix "0002", which always declines - so
 * "decline" is a deliberately reachable, testable path, not just a hardcoded always-success stub.
 */
@Component
public class SimulatedPaymentGateway implements PaymentGateway {

    private static final String ALWAYS_DECLINE_SUFFIX = "0002";

    @Override
    public PaymentResult charge(PaymentRequest request) {
        String digitsOnly = request.cardNumber().replaceAll("\\s+", "");

        if (!isValidLuhn(digitsOnly)) {
            return new PaymentResult(false, null, "Card number failed validation");
        }
        if (isExpired(request.expiryMonth(), request.expiryYear())) {
            return new PaymentResult(false, null, "Card has expired");
        }
        if (request.amountCents() <= 0) {
            return new PaymentResult(false, null, "Charge amount must be positive");
        }
        if (digitsOnly.endsWith(ALWAYS_DECLINE_SUFFIX)) {
            return new PaymentResult(false, null, "Card was declined by the issuing bank");
        }

        return new PaymentResult(true, UUID.randomUUID().toString(), "Payment approved");
    }

    private boolean isValidLuhn(String digitsOnly) {
        if (digitsOnly.isEmpty() || !digitsOnly.chars().allMatch(Character::isDigit)) {
            return false;
        }
        int sum = 0;
        boolean doubleDigit = false;
        for (int i = digitsOnly.length() - 1; i >= 0; i--) {
            int digit = digitsOnly.charAt(i) - '0';
            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }
        return sum % 10 == 0;
    }

    private boolean isExpired(int month, int year) {
        YearMonth expiry = YearMonth.of(year, month);
        return expiry.isBefore(YearMonth.now());
    }
}
