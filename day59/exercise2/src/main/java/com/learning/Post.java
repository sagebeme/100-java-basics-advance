package com.learning;

public class Post {
    private final Long id;
    private final String title;
    private final String category;

    public Post(Long id, String title, String category) {
        this.id = id;
        this.title = title;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
}
