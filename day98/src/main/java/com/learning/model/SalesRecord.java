package com.learning.model;

import java.time.LocalDate;

public record SalesRecord(LocalDate date, String category, String region, double amount) {
}
