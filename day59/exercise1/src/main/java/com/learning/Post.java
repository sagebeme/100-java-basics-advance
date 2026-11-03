package com.learning;

import java.time.LocalDate;
import java.util.List;

// One story. Already written. Paragraphs in the body are separated by a blank line.
public class Post {

    private final long id;
    private final String title;
    private final String author;
    private final LocalDate date;
    private final String body;

    public Post(long id, String title, String author, LocalDate date, String body) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public List<String> getParagraphs() {
        return List.of(body.split("\\n\\s*\\n"));
    }
}
