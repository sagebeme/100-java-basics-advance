package com.learning;

public class Book {

    private final long id;
    private final String title;
    private boolean available;

    public Book(long id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
