package com.learning.webhook;

import com.learning.model.MessageDirection;
import com.learning.model.MessageStatus;
import com.learning.model.WhatsAppMessage;
import com.learning.repository.WhatsAppMessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Locale;

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
    private final byte[] appSecret;
    private final WhatsAppMessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    public WebhookController(@Value("${whatsapp.webhook.verify-token}") String verifyToken,
                              @Value("${whatsapp.app-secret}") String appSecret,
                              WhatsAppMessageRepository messageRepository,
                              ObjectMapper objectMapper) {
        this.verifyToken = verifyToken;
        this.appSecret = appSecret.getBytes(StandardCharsets.UTF_8);
        this.messageRepository = messageRepository;
        this.objectMapper = objectMapper;
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

    /**
     * Meta signs every webhook: the X-Hub-Signature-256 header is "sha256=" plus the HMAC-SHA256
     * of the exact request bytes, keyed with the app secret. Anyone can POST to a public URL, so
     * a request without a valid signature is refused before anything is read or saved.
     */
    @PostMapping
    public ResponseEntity<String> receiveEvent(
            @RequestHeader(value = "X-Hub-Signature-256", required = false) String signature,
            @RequestBody byte[] rawBody) {

        if (!hasValidSignature(signature, rawBody)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
        }

        JsonNode payload;
        try {
            payload = objectMapper.readTree(rawBody);
        } catch (JacksonException e) {
            return ResponseEntity.badRequest().body("Invalid JSON");
        }

        JsonNode entries = payload.get("entry");
        if (entries != null) {
            for (JsonNode entry : entries) {
                processEntry(entry);
            }
        }
        return ResponseEntity.ok("EVENT_RECEIVED");
    }

    private boolean hasValidSignature(String header, byte[] body) {
        if (header == null || !header.startsWith("sha256=")) {
            return false;
        }
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(appSecret, "HmacSHA256"));
            byte[] expected = HexFormat.of().formatHex(mac.doFinal(body)).getBytes(StandardCharsets.US_ASCII);
            byte[] given = header.substring("sha256=".length()).toLowerCase(Locale.ROOT).getBytes(StandardCharsets.US_ASCII);
            // Constant-time comparison, so response timing doesn't leak how much of a guess was right.
            return MessageDigest.isEqual(expected, given);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("HmacSHA256 is not available", e);
        }
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
        // Meta redelivers a webhook it thinks we missed, so the same message can arrive twice.
        if (externalId != null && messageRepository.existsByExternalMessageId(externalId)) {
            return;
        }
        String body = "";
        if (incoming.has("text") && incoming.get("text").has("body")) {
            body = incoming.get("text").get("body").asString();
        }
        messageRepository.save(new WhatsAppMessage(MessageDirection.INBOUND, from, null, body, MessageStatus.RECEIVED, externalId));
    }
}
