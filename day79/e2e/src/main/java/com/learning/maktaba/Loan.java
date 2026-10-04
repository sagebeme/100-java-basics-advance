package com.learning.maktaba;

import java.time.LocalDate;

public record Loan(String code, long bookId, String bookTitle, String memberNumber, LocalDate due) {}
