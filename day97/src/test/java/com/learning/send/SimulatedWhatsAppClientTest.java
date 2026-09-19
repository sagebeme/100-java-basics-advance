package com.learning.send;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulatedWhatsAppClientTest {

    private final SimulatedWhatsAppClient client = new SimulatedWhatsAppClient();

    @Test
    void sendsSuccessfullyToAValidE164Number() {
        SendResult result = client.sendMessage("+14155552671", "Hello there");

        assertTrue(result.success());
        assertNotNull(result.externalMessageId());
        assertTrue(result.externalMessageId().startsWith("wamid."));
        assertNull(result.errorMessage());
    }

    @Test
    void rejectsANumberMissingThePlusPrefix() {
        SendResult result = client.sendMessage("14155552671", "Hello there");

        assertFalse(result.success());
        assertNull(result.externalMessageId());
        assertNotNull(result.errorMessage());
    }

    @Test
    void rejectsANumberThatIsTooShort() {
        SendResult result = client.sendMessage("+123", "Hello there");
        assertFalse(result.success());
    }

    @Test
    void rejectsABlankMessageBody() {
        SendResult result = client.sendMessage("+14155552671", "   ");
        assertFalse(result.success());
    }

    @Test
    void rejectsANullNumber() {
        SendResult result = client.sendMessage(null, "Hello there");
        assertFalse(result.success());
    }

    @Test
    void generatesADifferentIdForEverySuccessfulSend() {
        SendResult first = client.sendMessage("+14155552671", "one");
        SendResult second = client.sendMessage("+14155552671", "two");

        assertNotEquals(first.externalMessageId(), second.externalMessageId());
    }
}
