package com.learning.webhook;

import com.learning.model.MessageDirection;
import com.learning.model.MessageStatus;
import com.learning.model.WhatsAppMessage;
import com.learning.repository.WhatsAppMessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

/**
 * Implements the real WhatsApp Cloud API webhook contract - the verification handshake (GET) and
 * the message-received callback (POST) - against the exact JSON shape Meta documents. This side
 * doesn't need a Meta developer account: any server can expose this endpoint and it behaves
 * correctly whether or not a real WhatsApp Business account is ever configured to call it.
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final String verifyToken;
    private final WhatsAppMessageRepository messageRepository;

    public WebhookController(@Value("${whatsapp.webhook.verify-token}") String verifyToken,
                              WhatsAppMessageRepository messageRepository) {
        this.verifyToken = verifyToken;
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public ResponseEntity<String> verify(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge) {

        if ("subscribe".equals(mode) && verifyToken.equals(token)) {
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(403).body("Verification failed");
    }

    @PostMapping
    public ResponseEntity<String> receiveEvent(@RequestBody JsonNode payload) {
        JsonNode entries = payload.get("entry");
        if (entries != null) {
            for (JsonNode entry : entries) {
                processEntry(entry);
            }
        }
        return ResponseEntity.ok("EVENT_RECEIVED");
    }

    private void processEntry(JsonNode entry) {
        JsonNode changes = entry.get("changes");
        if (changes == null) {
            return;
        }
        for (JsonNode change : changes) {
            JsonNode value = change.get("value");
            if (value == null) {
                continue;
            }
            JsonNode messages = value.get("messages");
            if (messages == null) {
                continue;
            }
            for (JsonNode incoming : messages) {
                saveIncomingMessage(incoming);
            }
        }
    }

    private void saveIncomingMessage(JsonNode incoming) {
        String from = incoming.has("from") ? incoming.get("from").asString() : null;
        String externalId = incoming.has("id") ? incoming.get("id").asString() : null;
        String body = "";
        if (incoming.has("text") && incoming.get("text").has("body")) {
            body = incoming.get("text").get("body").asString();
        }
        messageRepository.save(new WhatsAppMessage(MessageDirection.INBOUND, from, null, body, MessageStatus.RECEIVED, externalId));
    }
}
