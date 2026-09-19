package com.learning.send;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Stands in for a real WhatsApp Cloud API call, since that needs a Meta developer account and API
 * credentials this environment doesn't have. Performs the same E.164 phone number format check a
 * real client-side validation would before ever attempting to send, and a blank message body is
 * rejected the same way the real API rejects it.
 */
@Component
public class SimulatedWhatsAppClient implements WhatsAppClient {

    private static final Pattern E164_PATTERN = Pattern.compile("^\\+[1-9]\\d{6,14}$");

    @Override
    public SendResult sendMessage(String toNumber, String body) {
        if (toNumber == null || !E164_PATTERN.matcher(toNumber).matches()) {
            return new SendResult(false, null, "Recipient number must be in E.164 format, e.g. +14155552671");
        }
        if (body == null || body.isBlank()) {
            return new SendResult(false, null, "Message body must not be blank");
        }
        return new SendResult(true, "wamid.simulated-" + UUID.randomUUID(), null);
    }
}
