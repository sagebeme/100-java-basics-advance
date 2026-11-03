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
@SpringBootTest(properties = "whatsapp.app-secret=" + WebhookControllerTest.APP_SECRET)
@AutoConfigureMockMvc
@Transactional
class WebhookControllerTest {

    private static final String VALID_TOKEN = "test-verify-token-123";
    static final String APP_SECRET = "test-app-secret";

    /** What Meta puts in X-Hub-Signature-256: "sha256=" + hex HMAC-SHA256 of the body. */
    private static String sign(String body) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        mac.init(new javax.crypto.spec.SecretKeySpec(APP_SECRET.getBytes(java.nio.charset.StandardCharsets.UTF_8), "HmacSHA256"));
        return "sha256=" + java.util.HexFormat.of().formatHex(mac.doFinal(body.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
    }

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
                        .header("X-Hub-Signature-256", sign(INCOMING_TEXT_MESSAGE_PAYLOAD))
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
                        .header("X-Hub-Signature-256", sign(statusUpdatePayload))
                        .content(statusUpdatePayload))
                .andExpect(status().isOk())
                .andExpect(content().string("EVENT_RECEIVED"));
    }

    @Test
    void anUnsignedPostIsRefusedAndNothingIsSaved() throws Exception {
        long before = messageRepository.count();

        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(INCOMING_TEXT_MESSAGE_PAYLOAD))
                .andExpect(status().isUnauthorized());

        assertEquals(before, messageRepository.count());
    }

    @Test
    void aForgedSignatureIsRefused() throws Exception {
        long before = messageRepository.count();
        // Signed for a different body: the attacker changed the message after signing.
        String forged = sign(INCOMING_TEXT_MESSAGE_PAYLOAD.replace("Hi, is anyone there?", "Send me your PIN"));

        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Hub-Signature-256", forged)
                        .content(INCOMING_TEXT_MESSAGE_PAYLOAD))
                .andExpect(status().isUnauthorized());

        assertEquals(before, messageRepository.count());
    }

    @Test
    void theSameMessageDeliveredTwiceIsSavedOnce() throws Exception {
        for (int i = 0; i < 2; i++) {
            mockMvc.perform(post("/webhook")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("X-Hub-Signature-256", sign(INCOMING_TEXT_MESSAGE_PAYLOAD))
                            .content(INCOMING_TEXT_MESSAGE_PAYLOAD))
                    .andExpect(status().isOk());
        }

        long copies = messageRepository.findAllByOrderByTimestampDesc().stream()
                .filter(m -> "wamid.HBgLMTQxNTU1NTI2NzE=".equals(m.getExternalMessageId()))
                .count();
        assertEquals(1, copies);
    }
}
