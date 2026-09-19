package com.learning.controller;

import com.learning.model.MessageDirection;
import com.learning.model.MessageStatus;
import com.learning.model.WhatsAppMessage;
import com.learning.repository.WhatsAppMessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MessagingFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WhatsAppMessageRepository messageRepository;

    @Test
    void theHomePageShowsTheComposeFormAndMessageHistory() throws Exception {
        messageRepository.save(new WhatsAppMessage(MessageDirection.INBOUND, "14155552671", null,
                "Hello from a customer", MessageStatus.RECEIVED, "wamid.abc"));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Message History")))
                .andExpect(content().string(containsString("Hello from a customer")));
    }

    @Test
    void sendingAValidMessageThroughTheFormPersistsItAsSent() throws Exception {
        mockMvc.perform(post("/messages/send")
                        .param("toNumber", "+14155552671")
                        .param("body", "Your order has shipped"))
                .andExpect(status().is3xxRedirection());

        WhatsAppMessage saved = messageRepository.findAllByOrderByTimestampDesc().get(0);
        assertEquals(MessageDirection.OUTBOUND, saved.getDirection());
        assertEquals(MessageStatus.SENT, saved.getStatus());
        assertEquals("+14155552671", saved.getToNumber());
        assertNotNull(saved.getExternalMessageId());
    }

    @Test
    void sendingToAnInvalidNumberPersistsItAsFailed() throws Exception {
        mockMvc.perform(post("/messages/send")
                        .param("toNumber", "not-a-number")
                        .param("body", "Your order has shipped"))
                .andExpect(status().is3xxRedirection());

        WhatsAppMessage saved = messageRepository.findAllByOrderByTimestampDesc().get(0);
        assertEquals(MessageStatus.FAILED, saved.getStatus());
        assertNull(saved.getExternalMessageId());
    }
}
