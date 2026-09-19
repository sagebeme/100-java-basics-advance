package com.learning.send;

public record SendResult(boolean success, String externalMessageId, String errorMessage) {
}
