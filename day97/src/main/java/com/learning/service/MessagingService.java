package com.learning.service;

import com.learning.model.MessageDirection;
import com.learning.model.MessageStatus;
import com.learning.model.WhatsAppMessage;
import com.learning.repository.WhatsAppMessageRepository;
import com.learning.send.SendResult;
import com.learning.send.WhatsAppClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingService {

    private final WhatsAppClient whatsAppClient;
    private final WhatsAppMessageRepository messageRepository;

    public MessagingService(WhatsAppClient whatsAppClient, WhatsAppMessageRepository messageRepository) {
        this.whatsAppClient = whatsAppClient;
        this.messageRepository = messageRepository;
    }

    /**
     * Sends a message and records the outcome either way - a failed send is still a real message
     * record with FAILED status, not silently dropped.
     */
    public WhatsAppMessage sendMessage(String toNumber, String body) {
        SendResult result = whatsAppClient.sendMessage(toNumber, body);
        WhatsAppMessage message = new WhatsAppMessage(
                MessageDirection.OUTBOUND,
                null,
                toNumber,
                body,
                result.success() ? MessageStatus.SENT : MessageStatus.FAILED,
                result.externalMessageId());
        return messageRepository.save(message);
    }

    public List<WhatsAppMessage> listMessages() {
        return messageRepository.findAllByOrderByTimestampDesc();
    }
}
