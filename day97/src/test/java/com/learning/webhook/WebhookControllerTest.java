package com.learning.webhook;

import com.learning.model.MessageDirection;
import com.learning.model.WhatsAppMessage;
import com.learning.repository.WhatsAppMessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests the real WhatsApp Cloud API webhook contract - the GET verification handshake and the
 * POST message-received callback - against JSON payloads shaped exactly like Meta's documented
 * schema, without needing a real Meta developer account or an actual call from WhatsApp's servers.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WebhookControllerTest {

    private static final String VALID_TOKEN = "test-verify-token-123";

    // Matches Meta's real documented webhook payload shape for an incoming text message.
    private static final String INCOMING_TEXT_MESSAGE_PAYLOAD = """
            {
              "object": "whatsapp_business_account",
              "entry": [{
                "id": "123456789",
                "changes": [{
                  "value": {
                    "messaging_product": "whatsapp",
                    "metadata": {
                      "display_phone_number": "15550001111",
                      "phone_number_id": "987654321"
                    },
                    "contacts": [{
                      "profile": { "name": "Amina" },
                      "wa_id": "14155552671"
                    }],
                    "messages": [{
                      "from": "14155552671",
                      "id": "wamid.HBgLMTQxNTU1NTI2NzE=",
                      "timestamp": "1730000000",
                      "text": { "body": "Hi, is anyone there?" },
                      "type": "text"
                    }]
                  },
                  "field": "messages"
                }]
              }]
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WhatsAppMessageRepository messageRepository;

    @Test
    void verificationHandshakeEchoesTheChallengeWhenTheTokenMatches() throws Exception {
        mockMvc.perform(get("/webhook")
                        .param("hub.mode", "subscribe")
                        .param("hub.verify_token", VALID_TOKEN)
                        .param("hub.challenge", "1158201444"))
                .andExpect(status().isOk())
                .andExpect(content().string("1158201444"));
    }

    @Test
    void verificationHandshakeRejectsTheWrongToken() throws Exception {
        mockMvc.perform(get("/webhook")
                        .param("hub.mode", "subscribe")
                        .param("hub.verify_token", "wrong-token")
                        .param("hub.challenge", "1158201444"))
                .andExpect(status().isForbidden());
    }

    @Test
    void verificationHandshakeRejectsAModeOtherThanSubscribe() throws Exception {
        mockMvc.perform(get("/webhook")
                        .param("hub.mode", "unsubscribe")
                        .param("hub.verify_token", VALID_TOKEN)
                        .param("hub.challenge", "1158201444"))
                .andExpect(status().isForbidden());
    }

    @Test
    void incomingMessageWebhookSavesAnInboundMessageAndReturnsEventReceived() throws Exception {
        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(INCOMING_TEXT_MESSAGE_PAYLOAD))
                .andExpect(status().isOk())
                .andExpect(content().string("EVENT_RECEIVED"));

        List<WhatsAppMessage> messages = messageRepository.findAllByOrderByTimestampDesc();
        WhatsAppMessage saved = messages.stream()
                .filter(m -> "wamid.HBgLMTQxNTU1NTI2NzE=".equals(m.getExternalMessageId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Expected the incoming message to be saved"));

        assertEquals(MessageDirection.INBOUND, saved.getDirection());
        assertEquals("14155552671", saved.getFromNumber());
        assertEquals("Hi, is anyone there?", saved.getBody());
    }

    @Test
    void aPayloadWithNoMessagesIsAcceptedWithoutError() throws Exception {
        String statusUpdatePayload = """
                {
                  "object": "whatsapp_business_account",
                  "entry": [{
                    "id": "123456789",
                    "changes": [{
                      "value": {
                        "messaging_product": "whatsapp",
                        "metadata": { "display_phone_number": "15550001111", "phone_number_id": "987654321" },
                        "statuses": [{ "id": "wamid.abc", "status": "delivered" }]
                      },
                      "field": "messages"
                    }]
                  }]
                }
                """;

        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(statusUpdatePayload))
                .andExpect(status().isOk())
                .andExpect(content().string("EVENT_RECEIVED"));
    }
}
