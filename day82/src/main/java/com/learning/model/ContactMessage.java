package com.learning.model;

import java.time.LocalDateTime;

public record ContactMessage(String name, String email, String message, LocalDateTime submittedAt) {
}
