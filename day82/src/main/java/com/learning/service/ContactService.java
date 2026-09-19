package com.learning.service;

import com.learning.model.ContactForm;
import com.learning.model.ContactMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ContactService {

    private final List<ContactMessage> messages = new CopyOnWriteArrayList<>();

    public ContactMessage save(ContactForm form) {
        ContactMessage saved = new ContactMessage(form.getName(), form.getEmail(), form.getMessage(), LocalDateTime.now());
        messages.add(saved);
        return saved;
    }

    public List<ContactMessage> listMessages() {
        return Collections.unmodifiableList(messages);
    }
}
