package com.learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class WhatsAppMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageDirection direction;

    private String fromNumber;
    private String toNumber;
    private String body;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private String externalMessageId;
    private LocalDateTime timestamp;

    protected WhatsAppMessage() {
    }

    public WhatsAppMessage(MessageDirection direction, String fromNumber, String toNumber, String body,
                            MessageStatus status, String externalMessageId) {
        this.direction = direction;
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.body = body;
        this.status = status;
        this.externalMessageId = externalMessageId;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public MessageDirection getDirection() {
        return direction;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public String getBody() {
        return body;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public String getExternalMessageId() {
        return externalMessageId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
