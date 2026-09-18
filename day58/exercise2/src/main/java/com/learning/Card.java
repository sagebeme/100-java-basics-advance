package com.learning;

public class Card {
    private final String title;
    private final String text;

    public Card(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() { return title; }
    public String getText() { return text; }
}
