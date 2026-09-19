package com.learning.model;

public enum PriceLevel {
    ONE("$"), TWO("$$"), THREE("$$$");

    private final String symbol;

    PriceLevel(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
