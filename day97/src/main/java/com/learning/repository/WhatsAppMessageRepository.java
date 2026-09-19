package com.learning.repository;

import com.learning.model.WhatsAppMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhatsAppMessageRepository extends JpaRepository<WhatsAppMessage, Long> {

    List<WhatsAppMessage> findAllByOrderByTimestampDesc();
}
