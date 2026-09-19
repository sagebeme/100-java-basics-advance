package com.portfolio;

import java.time.Instant;

public record SavedEntry(String content, Instant savedAt) {
}
