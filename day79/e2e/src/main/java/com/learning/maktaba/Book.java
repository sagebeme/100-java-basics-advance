package com.learning.maktaba;

public class Book {

    private final long id;
    private final String title;
    private final String author;
    private int copiesLeft;

    public Book(long id, String title, String author, int copiesLeft) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copiesLeft = copiesLeft;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getCopiesLeft() { return copiesLeft; }
    public boolean isAvailable() { return copiesLeft > 0; }

    void takeOneCopy() {
        if (copiesLeft == 0) throw new NoCopiesLeftException(title);
        copiesLeft--;
    }
}
